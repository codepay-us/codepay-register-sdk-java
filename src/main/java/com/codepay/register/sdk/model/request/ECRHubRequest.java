package com.codepay.register.sdk.model.request;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.codepay.register.sdk.ECRHubConfig;
import com.codepay.register.sdk.model.response.ECRHubResponse;

import java.util.Map;

public abstract class ECRHubRequest<T extends ECRHubResponse> {

    /**
     * Version number, temporarily fixed: 1.0
     */
    @JSONField(name = "version")
    private String version = "1.0";
    /**
     * Transaction request ID, used to receive the corresponding response. The caller needs to remain unique.
     */
    @JSONField(name = "requestId")
    private String request_id;
    /**
     * Payment Application Id
     */
    @JSONField(name = "appId")
    private String app_id;
    /**
     * Extended parameters
     */
    @JSONField(name = "extendsParams")
    private Map<String, String> extends_params;
    /**
     * Voice data object
     */
    @JSONField(name = "voiceData")
    private VoiceData voice_data;
    /**
     * Printer data object
     */
    @JSONField(name = "printerData")
    private PrinterData printer_data;
    /**
     * Notify data object
     */
    @JSONField(name = "notifyData")
    private NotifyData notify_data;
    /**
     * Request config object
     */
    private ECRHubConfig config;

    @JSONField(serialize = false)
    public Class<T> getResponseClass() {
        return (Class<T>) ClassUtil.getTypeArgument(getClass());
    }

    public abstract String getTopic();

    public ECRHubConfig getConfig() {
        return config;
    }

    public void setConfig(ECRHubConfig config) {
        this.config = config;
    }

    public String getRequest_id() {
        if (StrUtil.isBlank(request_id)) {
            request_id = IdUtil.fastSimpleUUID();
        }
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

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public Map<String, String> getExtends_params() {
        return extends_params;
    }

    public void setExtends_params(Map<String, String> extends_params) {
        this.extends_params = extends_params;
    }

    public VoiceData getVoice_data() {
        return voice_data;
    }

    public void setVoice_data(VoiceData voice_data) {
        this.voice_data = voice_data;
    }

    public PrinterData getPrinter_data() {
        return printer_data;
    }

    public void setPrinter_data(PrinterData printer_data) {
        this.printer_data = printer_data;
    }

    public NotifyData getNotify_data() {
        return notify_data;
    }

    public void setNotify_data(NotifyData notify_data) {
        this.notify_data = notify_data;
    }

    /**
     * Voice data object
     */
    public static class VoiceData {
        /**
         * Voice content
         */
        @JSONField(name = "content")
        private String content;
        /**
         * Voice content local
         */
        @JSONField(name = "contentLocale")
        private String content_locale;
        /**
         * Voice content url
         */
        @JSONField(name = "contentUrl")
        private String content_url;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent_locale() {
            return content_locale;
        }

        public void setContent_locale(String content_locale) {
            this.content_locale = content_locale;
        }

        public String getContent_url() {
            return content_url;
        }

        public void setContent_url(String content_url) {
            this.content_url = content_url;
        }
    }

    /**
     * Printer data object
     */
    public static class PrinterData {
        /**
         * Printer content
         */
        @JSONField(name = "content")
        private String content;
        /**
         * Printer content url
         */
        @JSONField(name = "contentUrl")
        private String content_url;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent_url() {
            return content_url;
        }

        public void setContent_url(String content_url) {
            this.content_url = content_url;
        }
    }

    /**
     * Notify data object
     */
    public static class NotifyData {
        /**
         * Notify title
         */
        @JSONField(name = "title")
        private String title;
        /**
         * Notify body
         */
        @JSONField(name = "body")
        private String body;
        /**
         * Notify image url
         */
        @JSONField(name = "imageUrl")
        private String image_url;
        /**
         * Notify sound
         */
        @JSONField(name = "sound")
        private String sound;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getSound() {
            return sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}