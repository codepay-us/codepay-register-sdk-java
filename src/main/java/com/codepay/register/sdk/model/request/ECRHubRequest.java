package com.codepay.register.sdk.model.request;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.codepay.register.sdk.ECRHubConfig;
import com.codepay.register.sdk.model.response.ECRHubResponse;

public abstract class ECRHubRequest<T extends ECRHubResponse> {
    /**
     * Message topic identifier
     *
     * Example: ecrhub.pay.order
     */
    private String topic;
    /**
     * Payment Application Id
     */
    private String app_id;
    /**
     * Transaction request ID, used to receive the corresponding response. The caller needs to remain unique.
     */
    private String request_id = IdUtil.fastSimpleUUID();;
    /**
     * Version number, temporarily fixed: 1.0
     */
    private String version = "1.0";
    /**
     * Transaction request ID, used to receive the corresponding response. The caller needs to remain unique.
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * Request config object
     */
    private ECRHubConfig config;

    @JSONField(serialize = false)
    public Class<T> getResponseClass() {
        return (Class<T>) ClassUtil.getTypeArgument(getClass());
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ECRHubConfig getConfig() {
        return config;
    }

    public void setConfig(ECRHubConfig config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}