package com.codepay.register.sdk.model.response;

import java.util.Map;

public class VoidResponse extends ECRHubResponse {
    /**
     * Transaction type
     *
     * @see com.codepay.register.sdk.enums.ETransType
     *
     * For example: 1
     */
    private String trans_type;
    /**
     * Merchant order No.
     * The order number for the refund request when refunded, different from the order number of the original consumer transaction. No more than 32 alphanumeric characters.
     *
     * For example: 1217752501201407033233368018
     */
    private String merchant_order_no;
    /**
     * Original Merchant Order No.
     * Required, if the transaction type is Cancellation, Refund and Pre-Authorization Cancellation, Pre-Authorization Completion.
     *
     * For example: 1217752501201407033233368017
     */
    private String orig_merchant_order_no;
    /**
     * Payment Channel Transaction No. such as WeChat, Alipay, Visa, Mastercard and other payment platforms
     *
     * For example: 4210001022202106045676702818
     */
    private String orig_pay_channel_trans_no;
    /**
     * Price Currency, ISO-4217 compliant, described in a three-character code
     *
     * For example: USD
     */
    private String price_currency;
    /**
     * Order Amount
     * Expressed in the quoted currency, for example, One USD stands for one dollar, not one cent
     *
     * For example: 34.50
     */
    private String order_amount;
    /**
     * Tip Amount
     * The amount of the tip is expressed in the currency in which it is denominated, for example, 1 USD stands for one dollar, not one cent.
     *
     * For example: 1.50
     */
    private String tip_amount;
    /**
     * Cashback Amount
     * The amount of the tip is expressed in the currency in which it is denominated, for example, 1 USD stands for one dollar, not one cent.
     *
     * For example: 1.00
     */
    private String cashback_amount;
    /**
     * Payment scenario
     *
     * @see com.codepay.register.sdk.enums.EPayScenario
     */
    private String pay_scenario;
    /**
     * Payment method id
     *
     * @see com.codepay.register.sdk.enums.EPayMethod
     *
     * For example: Visa
     */
    private String pay_method_id;
    /**
     * Attach
     * Allows merchants to submit an additional data to the gateway, which will be returned as-is for payment notifications and inquiries
     *
     * For example: abc
     */
    private String attach;
    /**
     * Transaction status
     *
     * @see com.codepay.register.sdk.enums.ETransStatus
     *
     * For example: 2
     */
    private String trans_status;
    /**
     * PayCloud transaction No.
     *
     * For example: 51230016492309010000001
     */
    private String trans_no;
    /**
     * Payment Channel Transaction No. such as WeChat, Alipay, Visa, Mastercard and other payment platforms
     *
     * For example: 4210001022202106045676702818
     */
    private String pay_channel_trans_no;
    /**
     * Merchant discount amount
     *
     * For example: 10.00
     */
    private String discount_bmopc;
    /**
     * Payment Channel discount amount
     *
     * For example: 6.00
     */
    private String discount_bpc;
    /**
     * Time of successful trade, format: YYYY-MM-DD HH:mm:ss
     *
     * For Example: 2021-06-03 12:48:51
     */
    private String trans_end_time;
    /**
     * Extended parameters
     */
    private Map<String, String> extends_params;

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public String getMerchant_order_no() {
        return merchant_order_no;
    }

    public void setMerchant_order_no(String merchant_order_no) {
        this.merchant_order_no = merchant_order_no;
    }

    public String getPrice_currency() {
        return price_currency;
    }

    public void setPrice_currency(String price_currency) {
        this.price_currency = price_currency;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getTip_amount() {
        return tip_amount;
    }

    public void setTip_amount(String tip_amount) {
        this.tip_amount = tip_amount;
    }

    public String getPay_scenario() {
        return pay_scenario;
    }

    public void setPay_scenario(String pay_scenario) {
        this.pay_scenario = pay_scenario;
    }

    public String getPay_method_id() {
        return pay_method_id;
    }

    public void setPay_method_id(String pay_method_id) {
        this.pay_method_id = pay_method_id;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTrans_status() {
        return trans_status;
    }

    public void setTrans_status(String trans_status) {
        this.trans_status = trans_status;
    }

    public String getTrans_no() {
        return trans_no;
    }

    public void setTrans_no(String trans_no) {
        this.trans_no = trans_no;
    }

    public String getPay_channel_trans_no() {
        return pay_channel_trans_no;
    }

    public void setPay_channel_trans_no(String pay_channel_trans_no) {
        this.pay_channel_trans_no = pay_channel_trans_no;
    }

    public String getDiscount_bmopc() {
        return discount_bmopc;
    }

    public void setDiscount_bmopc(String discount_bmopc) {
        this.discount_bmopc = discount_bmopc;
    }

    public String getDiscount_bpc() {
        return discount_bpc;
    }

    public void setDiscount_bpc(String discount_bpc) {
        this.discount_bpc = discount_bpc;
    }

    public String getTrans_end_time() {
        return trans_end_time;
    }

    public void setTrans_end_time(String trans_end_time) {
        this.trans_end_time = trans_end_time;
    }

    public Map<String, String> getExtends_params() {
        return extends_params;
    }

    public void setExtends_params(Map<String, String> extends_params) {
        this.extends_params = extends_params;
    }
}