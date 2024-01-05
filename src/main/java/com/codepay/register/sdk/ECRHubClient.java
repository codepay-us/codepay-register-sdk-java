package com.codepay.register.sdk;

import com.codepay.register.sdk.exception.ECRHubException;
import com.codepay.register.sdk.model.request.ECRHubRequest;
import com.codepay.register.sdk.model.response.ECRHubResponse;

public interface ECRHubClient {

    boolean connect() throws ECRHubException;

    boolean isConnected() throws ECRHubException;

    boolean disconnect() throws ECRHubException;

    <T extends ECRHubResponse> T execute(ECRHubRequest<T> request) throws ECRHubException;

    <T extends ECRHubResponse> void asyncExecute(ECRHubRequest<T> request, ECRHubResponseCallBack<T> callback) throws ECRHubException;

}