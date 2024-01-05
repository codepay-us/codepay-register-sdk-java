package com.codepay.register.sdk.model.request;

import com.codepay.register.sdk.enums.ETopic;
import com.codepay.register.sdk.model.response.QueryResponse;

public class QueryRequest extends ECRHubRequest<QueryResponse> {

    @Override
    public String getTopic() {
        return ETopic.QUERY_ORDER.getVal();
    }

    /**
     * Merchant order No.
     * The order number for the refund request when refunded, different from the order number of the original consumer transaction. No more than 32 alphanumeric characters.
     *
     * For example: 1217752501201407033233368018
     */
    private String merchant_order_no;

    public String getMerchant_order_no() {
        return merchant_order_no;
    }

    public void setMerchant_order_no(String merchant_order_no) {
        this.merchant_order_no = merchant_order_no;
    }
}