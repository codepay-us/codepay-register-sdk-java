package com.codepay.ecr.hub.sdk;

import com.codepay.ecr.hub.sdk.exception.ECRHubException;
import com.codepay.ecr.hub.sdk.exception.ECRHubTimeoutException;
import com.codepay.ecr.hub.sdk.model.response.ECRHubResponse;

public interface ECRHubResponseCallBack<R extends ECRHubResponse> {

    void onResponse(R response);

    void onTimeout(ECRHubTimeoutException e);

    void onError(ECRHubException e);

}