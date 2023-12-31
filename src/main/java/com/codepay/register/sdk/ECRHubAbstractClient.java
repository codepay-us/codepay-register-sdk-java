package com.codepay.register.sdk;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSONObject;
import com.codepay.register.sdk.enums.EResponseCode;
import com.codepay.register.sdk.enums.ETopic;
import com.codepay.register.sdk.exception.ECRHubConnectionException;
import com.codepay.register.sdk.exception.ECRHubException;
import com.codepay.register.sdk.exception.ECRHubTimeoutException;
import com.codepay.register.sdk.model.request.ECRHubRequest;
import com.codepay.register.sdk.model.response.ECRHubResponse;
import com.codepay.register.sdk.model.response.ECRHubResponse.DeviceData;
import com.codepay.register.sdk.protobuf.ECRHubProtobufHelper;
import com.codepay.register.sdk.protobuf.ECRHubRequestProto;
import com.codepay.register.sdk.protobuf.ECRHubResponseProto;
import com.codepay.register.sdk.utils.NetHelper;

import java.util.Optional;

public abstract class ECRHubAbstractClient implements ECRHubClient {

    private final ECRHubConfig config;

    protected ECRHubAbstractClient(ECRHubConfig config) {
        this.config = config;
    }

    public ECRHubConfig getConfig() {
        return config;
    }

    @Override
    public <T extends ECRHubResponse> T execute(ECRHubRequest<T> request) throws ECRHubException {
        // Auto connect
        autoConnect();

        // Send request
        sendReq(request);

        // Sync receive response
        return getResp(request);
    }

    @Override
    public <T extends ECRHubResponse> void asyncExecute(ECRHubRequest<T> request, ECRHubResponseCallBack<T> callback) throws ECRHubException {
        // Auto connect
        autoConnect();

        // Send request
        sendReq(request);

        // Async receive response
        ThreadUtil.execute(() -> {
            try {
                callback.onResponse(getResp(request));
            } catch (ECRHubTimeoutException e) {
                callback.onTimeout(e);
            } catch (ECRHubException e) {
                callback.onError(e);
            } catch (Exception e) {
                callback.onError(new ECRHubException(e));
            }
        });
    }

    protected void autoConnect() throws ECRHubException {
    }

    protected abstract <T extends ECRHubResponse> void sendReq(ECRHubRequest<T> request) throws ECRHubException;

    protected abstract <T extends ECRHubResponse> T getResp(ECRHubRequest<T> request) throws ECRHubException;

    protected abstract ECRHubResponseProto.ECRHubResponse send(ECRHubRequestProto.ECRHubRequest request, long startTime) throws ECRHubException;

    protected ECRHubResponse pair(long startTime) throws ECRHubException {
        ECRHubResponseProto.ECRHubResponse resp = send(buildPairReq(), startTime);
        ECRHubResponse response = buildResp(ECRHubResponse.class, resp);
        if (!EResponseCode.SUCCESS.getCode().equals(response.getResponse_code())) {
            throw new ECRHubConnectionException("["+response.getResponse_code()+"]" + response.getResponse_msg());
        }
        return response;
    }

    protected ECRHubRequestProto.ECRHubRequest buildPairReq() {
        String hostName = Optional.ofNullable(config.getHostName()).orElse(NetHelper.getLocalHostName());
        String aliasName = Optional.ofNullable(config.getAliasName()).orElse(hostName);
        String macAddress = NetHelper.getLocalMacAddress();

        return ECRHubRequestProto.ECRHubRequest.newBuilder()
              .setTimestamp(String.valueOf(System.currentTimeMillis()))
              .setRequestId(IdUtil.fastSimpleUUID())
              .setTopic(ETopic.PAIR.getVal())
              .setDeviceData(ECRHubRequestProto.RequestDeviceData.newBuilder()
                            .setDeviceName(hostName)
                            .setAliasName(aliasName)
                            .setMacAddress(macAddress)
                            .build())
              .build();
    }

    protected <T extends ECRHubResponse> T buildResp(Class<T> respClass, byte[] respBuffer) throws ECRHubException {
        if (respBuffer != null) {
            ECRHubResponseProto.ECRHubResponse resp = ECRHubProtobufHelper.parseRespFrom(respBuffer);
            return buildResp(respClass, resp);
        } else {
            return null;
        }
    }

    protected <T extends ECRHubResponse> T buildResp(Class<T> respClass, ECRHubResponseProto.ECRHubResponse resp) throws ECRHubException {
        if (EResponseCode.TIMEOUT.getCode().equals(resp.getResponseCode())) {
            throw new ECRHubTimeoutException(resp.getResponseMsg());
        }

        DeviceData deviceData = null;
        if (resp.hasDeviceData()) {
            JSONObject json = ECRHubProtobufHelper.toJson(resp.getDeviceData());
            deviceData = json.toJavaObject(DeviceData.class);
        }

        ECRHubResponseProto.ResponseBizData respBizData = resp.getBizData();
        JSONObject respDataJson = ECRHubProtobufHelper.toJson(respBizData);

        T response = respDataJson.toJavaObject(respClass);
        response.setRequest_id(resp.getRequestId());
        response.setResponse_code(resp.getResponseCode());
        response.setResponse_msg(resp.getResponseMsg());
        response.setDevice_data(deviceData);
        return response;
    }
}