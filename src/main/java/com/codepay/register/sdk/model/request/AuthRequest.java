package com.codepay.register.sdk.model.request;

import com.codepay.register.sdk.enums.ETopic;
import com.codepay.register.sdk.enums.ETransType;
import com.codepay.register.sdk.model.response.AuthResponse;

import java.util.Map;

public class AuthRequest extends ECRHubRequest<AuthResponse> {

    @Override
    public String getTopic() {
        return ETopic.PAY_ORDER.getVal();
    }
    /**
     * Transaction type
     *
     * @see ETransType
     *
     * For example: 4
     */
    private String trans_type = ETransType.AUTHORIZATION.getCode();
    /**
     * Merchant order No.
     * The order number for the refund request when refunded, different from the order number of the original consumer transaction. No more than 32 alphanumeric characters.
     *
     * For example: 1217752501201407033233368018
     */
    private String merchant_order_no;
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
     * Whether or not to enter tips on the CodePay Register page, default is false
     *
     * Example: true
     */
    private boolean on_screen_tip;
    /**
     * Payment scene
     *
     * @see com.codepay.register.sdk.enums.EPayScenario
     *
     * For example: BANKCARD
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
     * Type of bank card
     *
     * For example: Visa
     */
    private String card_type;
    /**
     * Attach
     * Allows merchants to submit an additional data to the gateway, which will be returned as-is for payment notifications and inquiries
     *
     * For example: abc
     */
    private String attach;
    /**
     * Order description
     * A brief description of the goods or services purchased by the customer
     *
     * For example: IPhone White X2
     */
    private String description;
    /**
     * PayCloud backend server callback address after successful payment
     * Receive payment notifications from the gateway to call back the server address, and only when the transaction goes through the payment gateway will there be a callback.
     *
     * For example: http://www.xxx.com/notify
     */
    private String notify_url;
    /**
     * Order expires time, in seconds. Default to 300 seconds.
     *
     * For example: 300
     */
    private String expires;
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

    public boolean isOn_screen_tip() {
        return on_screen_tip;
    }

    public void setOn_screen_tip(boolean on_screen_tip) {
        this.on_screen_tip = on_screen_tip;
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

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public Map<String, String> getExtends_params() {
        return extends_params;
    }

    public void setExtends_params(Map<String, String> extends_params) {
        this.extends_params = extends_params;
    }
}