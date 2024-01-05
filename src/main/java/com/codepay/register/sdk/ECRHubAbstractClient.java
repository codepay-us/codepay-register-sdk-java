package com.codepay.register.sdk;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.codepay.register.sdk.enums.EResponseCode;
import com.codepay.register.sdk.exception.ECRHubException;
import com.codepay.register.sdk.exception.ECRHubTimeoutException;
import com.codepay.register.sdk.model.request.ECRHubRequest;
import com.codepay.register.sdk.model.response.ECRHubResponse;

import java.nio.charset.StandardCharsets;

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

    protected byte[] pack(ECRHubRequest request) throws ECRHubException {
        JSONObject bizData = JSON.parseObject(request.toString());
        bizData.remove("topic");
        bizData.remove("app_id");
        bizData.remove("request_id");
        bizData.remove("version");
        bizData.remove("timestamp");
        bizData.remove("config");

        JSONObject pack = new JSONObject();
        pack.put("topic", request.getTopic());
        pack.put("app_id", request.getApp_id());
        pack.put("request_id", request.getRequest_id());
        pack.put("version", request.getVersion());
        pack.put("timestamp", request.getTimestamp());
        pack.put("biz_data", bizData);
        return pack.toString().getBytes(StandardCharsets.UTF_8);
    }

    protected <T extends ECRHubResponse> T unpack(Class<T> respClass, byte[] respBuffer) throws ECRHubException {
        if (respBuffer != null) {
            return unpack(respClass, JSON.parseObject(respBuffer));
        } else {
            return null;
        }
    }

    protected <T extends ECRHubResponse> T unpack(Class<T> respClass, JSONObject respJson) throws ECRHubException {
        String respCode = respJson.getString("response_code");
        String respMsg = respJson.getString("response_msg");
        if (EResponseCode.TIMEOUT.getCode().equals(respCode)) {
            throw new ECRHubTimeoutException(respMsg);
        }

        JSONObject bizData = respJson.getJSONObject("biz_data");
        T response = bizData.toJavaObject(respClass);
        response.setTopic(respJson.getString("topic"));
        response.setApp_id(respJson.getString("app_id"));
        response.setRequest_id(respJson.getString("request_id"));
        response.setResponse_code(respCode);
        response.setResponse_msg(respMsg);
        return response;
    }
}