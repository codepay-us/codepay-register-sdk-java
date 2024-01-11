package com.codepay.register.sdk.device;

import com.codepay.register.sdk.exception.ECRHubException;

import java.util.List;

/**
 * @author wangyuxiang
 * @since 2023-10-20 11:34
 */
public interface ECRHubDiscoveryService {

    void start() throws ECRHubException;

    void stop() throws ECRHubException;

    boolean isRunning();

    void setDeviceEventListener(ECRHubDeviceEventListener listener);

    boolean pair(ECRHubDevice device) throws ECRHubException;

    void unpair(ECRHubDevice device) throws ECRHubException;

    List<ECRHubDevice> getPairedDeviceList();

    List<ECRHubDevice> getUnpairedDeviceList();

}
