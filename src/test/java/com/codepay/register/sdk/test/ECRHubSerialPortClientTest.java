package com.codepay.register.sdk.test;

import com.codepay.register.sdk.ECRHubClient;
import com.codepay.register.sdk.ECRHubClientFactory;
import com.codepay.register.sdk.ECRHubConfig;
import com.codepay.register.sdk.ECRHubResponseCallBack;
import com.codepay.register.sdk.enums.EOrderQueueMode;
import com.codepay.register.sdk.enums.EPayMethodCategory;
import com.codepay.register.sdk.exception.ECRHubException;
import com.codepay.register.sdk.exception.ECRHubTimeoutException;
import com.codepay.register.sdk.model.request.*;
import com.codepay.register.sdk.model.response.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test serial port client")
public class ECRHubSerialPortClientTest {

    public static final String APP_ID = "wz6012822ca2f1as78";
    public static final String SERIAL_PORT_NAME = "";

    private static ECRHubClient client;

    @BeforeAll
    public static void before() throws ECRHubException {
        client = ECRHubClientFactory.create("sp://" + SERIAL_PORT_NAME);
        client.connect(); // Must
    }

    @Test
    @DisplayName("paymentInit")
    public void paymentInit() throws ECRHubException {
        // Payment init
        PaymentInitRequest request = new PaymentInitRequest();
        request.setApp_id(APP_ID);
        request.setIs_auto_settlement(true);
        request.setConfirm_on_terminal(true);
        request.setOrder_queue_mode(EOrderQueueMode.FIFO.getVal());

        // Execute payment init request
        System.out.println("Payment init request:" + request);
        PaymentInitResponse response = client.execute(request);
        System.out.println("Payment init response:" + response);
    }

    @Test
    @DisplayName("purchase")
    public void purchase() throws ECRHubException {
        // Purchase
        PurchaseRequest request = new PurchaseRequest();
        request.setApp_id(APP_ID);
        request.setMerchant_order_no("O" + System.currentTimeMillis());
        request.setOrder_amount("10");
        request.setPay_method_category(EPayMethodCategory.BANKCARD.getVal());

        // Setting read timeout,the timeout set here is valid for this request
        ECRHubConfig requestConfig = new ECRHubConfig();
        requestConfig.getSerialPortConfig().setReadTimeout(5 * 60 * 1000);
        request.setConfig(requestConfig);

        // Execute purchase request
        System.out.println("Purchase request:" + request);
        PurchaseResponse response = client.execute(request);
        System.out.println("Purchase response:" + response);
    }

    @Test
    @DisplayName("purchase async")
    public void purchase_async() throws ECRHubException {
        // Purchase
        PurchaseRequest request = new PurchaseRequest();
        request.setApp_id(APP_ID);
        request.setMerchant_order_no("O" + System.currentTimeMillis());
        request.setOrder_amount("10");
        request.setTip_amount("2");
        request.setPay_method_category("BANKCARD");

        // Execute purchase request
        // Asynchronous return result
        client.asyncExecute(request, new ECRHubResponseCallBack<PurchaseResponse>() {
            @Override
            public void onResponse(PurchaseResponse response) {
                System.out.println("Purchase onCompleted:" + response);
            }

            @Override
            public void onTimeout(ECRHubTimeoutException e) {
                System.out.println("Purchase onTimeout.");
            }

            @Override
            public void onError(ECRHubException e) {
                System.out.println("Purchase onError:" + e.getMessage());
            }
        });
    }

    @Test
    @DisplayName("refund")
    public void refund() throws ECRHubException {
        RefundRequest request = new RefundRequest();
        request.setApp_id(APP_ID);
        request.setOrig_merchant_order_no("O1695032342508");
        request.setMerchant_order_no("O" + System.currentTimeMillis());
        request.setOrder_amount("1");
        request.setPay_method_category("BANKCARD");

        System.out.println("Refund request:" + request);
        RefundResponse response = client.execute(request);
        System.out.println("Refund response:" + response);
    }

    @Test
    @DisplayName("closeOrder")
    public void closeOrder() throws ECRHubException {
        CloseRequest request = new CloseRequest();
        request.setApp_id(APP_ID);
        request.setMerchant_order_no("O1695032342508");

        System.out.println("Close request:" + request);
        CloseResponse response = client.execute(request);
        System.out.println("Close response:" + response);
    }

    @Test
    @DisplayName("queryOrder")
    public void queryOrder() throws ECRHubException {
        QueryRequest request = new QueryRequest();
        request.setApp_id(APP_ID);
        request.setMerchant_order_no("O1695032342508");

        System.out.println("Query request:" + request);
        QueryResponse response = client.execute(request);
        System.out.println("Query response:" + response);
    }
}