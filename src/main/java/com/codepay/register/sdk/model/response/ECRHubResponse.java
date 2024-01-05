package com.codepay.register.sdk.model.response;

import com.alibaba.fastjson2.JSON;

public class ECRHubResponse {
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
    private String request_id;
    /**
     * Response Code, 000:success, other:failure
     */
    private String response_code;
    /**
     * Response message, this msg contains an error message when an error occurs
     */
    private String response_msg;

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

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public String getResponse_msg() {
        return response_msg;
    }

    public void setResponse_msg(String response_msg) {
        this.response_msg = response_msg;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}