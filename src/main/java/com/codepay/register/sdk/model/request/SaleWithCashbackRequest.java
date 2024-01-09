package com.codepay.register.sdk.model.request;

import com.codepay.register.sdk.enums.ETopic;
import com.codepay.register.sdk.enums.ETransType;
import com.codepay.register.sdk.model.response.SaleWithCashbackResponse;

import java.util.Map;

public class SaleWithCashbackRequest extends ECRHubRequest<SaleWithCashbackResponse> {

    @Override
    public String getTopic() {
        return ETopic.PAY_ORDER.getVal();
    }
    /**
     * Transaction type
     *
     * @see ETransType
     *
     * For example: 11
     */
    private String trans_type = ETransType.CASH_BACK.getCode();
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
     * Cashback amount. Expressed in the quoted currency, for example, One USD stands for one dollar, not one cent
     *
     * For example: 1.50
     */
    private String cashback_amount;
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
     * This parameter controls the display logic of electronic signatures:
     * true: Display the electronic signature page and print the signature information on the purchase order
     * false: The electronic signature page will not be displayed, but the signature area needs to be printed on the purchase order
     * But even if this parameter is set, CodePay Register still processes electronic signatures according to the following logic in the following situations:
     * Credit card network, APP does not display signature page, fixed on receipt to print signature column
     *
     * Pin debit transactions do not require a signature, the APP does not display a signature page, and the receipt is not printed either
     */
    private Boolean on_screen_signature;
    /**
     * Order need terminal confirmation. Default: true
     * - true: Terminal confirmation is required;
     * - false: No terminal confirmation is required.
     *
     * For example: true
     */
    private Boolean confirm_on_terminal;
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

    public String getCashback_amount() {
        return cashback_amount;
    }

    public void setCashback_amount(String cashback_amount) {
        this.cashback_amount = cashback_amount;
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

    public Boolean getOn_screen_signature() {
        return on_screen_signature;
    }

    public void setOn_screen_signature(Boolean on_screen_signature) {
        this.on_screen_signature = on_screen_signature;
    }

    public Boolean getConfirm_on_terminal() {
        return confirm_on_terminal;
    }

    public void setConfirm_on_terminal(Boolean confirm_on_terminal) {
        this.confirm_on_terminal = confirm_on_terminal;
    }

    public Map<String, String> getExtends_params() {
        return extends_params;
    }

    public void setExtends_params(Map<String, String> extends_params) {
        this.extends_params = extends_params;
    }
}