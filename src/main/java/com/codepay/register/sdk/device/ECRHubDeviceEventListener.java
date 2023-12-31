package com.codepay.register.sdk.device;

/**
 * @author wangyuxiang
 * @since 2023-10-20 11:39
 */
public interface ECRHubDeviceEventListener {


    void onAdded(ECRHubDevice device);

    void onRemoved(ECRHubDevice device);

    boolean onPaired(ECRHubDevice device);

    void onUnpaired(ECRHubDevice device);

}
