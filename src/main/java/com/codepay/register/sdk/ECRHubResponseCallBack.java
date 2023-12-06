package com.codepay.register.sdk;

import com.codepay.register.sdk.exception.ECRHubException;
import com.codepay.register.sdk.exception.ECRHubTimeoutException;
import com.codepay.register.sdk.model.response.ECRHubResponse;

public interface ECRHubResponseCallBack<R extends ECRHubResponse> {

    void onResponse(R response);

    void onTimeout(ECRHubTimeoutException e);

    void onError(ECRHubException e);

}