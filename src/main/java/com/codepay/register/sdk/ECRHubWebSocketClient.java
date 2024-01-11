package com.codepay.register.sdk;

import com.codepay.register.sdk.exception.ECRHubException;
import com.codepay.register.sdk.exception.ECRHubTimeoutException;
import com.codepay.register.sdk.model.request.ECRHubRequest;
import com.codepay.register.sdk.model.response.ECRHubResponse;
import com.codepay.register.sdk.sp.websocket.WebSocketClientEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ECRHubWebSocketClient extends ECRHubAbstractClient {

    private static final Logger log = LoggerFactory.getLogger(ECRHubWebSocketClient.class);

    private volatile boolean connected = false;

    private final WebSocketClientEngine engine;

    public ECRHubWebSocketClient(String url, ECRHubConfig config) throws ECRHubException {
        super(config);
        try {
            this.engine = new WebSocketClientEngine(new URI(url));
        } catch (Exception e) {
            throw new ECRHubException("ecrWebSocketClient error", e);
        }
    }

    @Override
    public boolean connect() throws ECRHubException {
        int timeout = getConfig().getSocketConfig().getConnTimeout();
        log.info("Connecting...");

        boolean success;
        try {
            success = engine.connectBlocking(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new ECRHubTimeoutException();
        }
        if (!success) {
            throw new ECRHubException("Connection failed");
        }

        connected = true;
        log.info("Connection successful");
        return true;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public boolean disconnect() throws ECRHubException {
        try {
            engine.closeBlocking();
            connected = false;
            return true;
        } catch (InterruptedException e) {
            throw new ECRHubException("disconnect error", e);
        }
    }

    @Override
    protected <T extends ECRHubResponse> void sendReq(ECRHubRequest<T> request) throws ECRHubException {
        byte[] buffer = pack(request);
        engine.send(buffer);
    }

    @Override
    protected <T extends ECRHubResponse> T getResp(ECRHubRequest<T> request) throws ECRHubException {
        ECRHubConfig config = Optional.ofNullable(request.getConfig()).orElse(super.getConfig());
        long timeout = config.getSocketConfig().getReadTimeout();

        byte[] buffer = engine.receive(request.getRequest_id(), System.currentTimeMillis(), timeout);
        return unpack(request.getResponseClass(), buffer);
    }
}