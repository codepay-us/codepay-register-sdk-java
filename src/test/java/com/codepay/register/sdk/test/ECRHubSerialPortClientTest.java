package com.codepay.register.sdk.test;

import com.codepay.register.sdk.ECRHubClient;
import com.codepay.register.sdk.ECRHubClientFactory;
import com.codepay.register.sdk.ECRHubConfig;
import com.codepay.register.sdk.enums.EOrderQueueMode;
import com.codepay.register.sdk.enums.EPayScenario;
import com.codepay.register.sdk.exception.ECRHubException;
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
    @DisplayName("Sale")
    public void Sale() throws ECRHubException {
        // Sale
        SaleRequest request = new SaleRequest();
        request.setApp_id(APP_ID);
        request.setMerchant_order_no("O" + System.currentTimeMillis());
        request.setOrder_amount("10");
        request.setPay_scenario(EPayScenario.SWIPE_CARD.getVal());

        // Setting read timeout,the timeout set here is valid for this request
        ECRHubConfig requestConfig = new ECRHubConfig();
        requestConfig.getSerialPortConfig().setReadTimeout(5 * 60 * 1000);
        request.setConfig(requestConfig);

        // Execute sale request
        System.out.println("Sale request:" + request);
        SaleResponse response = client.execute(request);
        System.out.println("Sale response:" + response);
    }

    @Test
    @DisplayName("SaleWithCashback")
    public void SaleWithCashback() throws ECRHubException {
        // Sale With Cashback
        SaleWithCashbackRequest request = new SaleWithCashbackRequest();
        request.setApp_id(APP_ID);
        request.setMerchant_order_no("O" + System.currentTimeMillis());
        request.setOrder_amount("10");
        request.setCashback_amount("5");
        request.setPay_scenario(EPayScenario.SWIPE_CARD.getVal());

        // Setting read timeout,the timeout set here is valid for this request
        ECRHubConfig requestConfig = new ECRHubConfig();
        requestConfig.getSerialPortConfig().setReadTimeout(5 * 60 * 1000);
        request.setConfig(requestConfig);

        // Execute sale with cashback request
        System.out.println("SaleWithCashback request:" + request);
        SaleWithCashbackResponse response = client.execute(request);
        System.out.println("SaleWithCashback response:" + response);
    }

    @Test
    @DisplayName("Auth")
    public void Auth() throws ECRHubException {
        // Auth
        AuthRequest request = new AuthRequest();
        request.setApp_id(APP_ID);
        request.setMerchant_order_no("O" + System.currentTimeMillis());
        request.setOrder_amount("10");
        request.setPay_scenario(EPayScenario.SWIPE_CARD.getVal());

        // Setting read timeout,the timeout set here is valid for this request
        ECRHubConfig requestConfig = new ECRHubConfig();
        requestConfig.getSerialPortConfig().setReadTimeout(5 * 60 * 1000);
        request.setConfig(requestConfig);

        // Execute auth request
        System.out.println("Auth request:" + request);
        AuthResponse response = client.execute(request);
        System.out.println("Auth response:" + response);
    }

    @Test
    @DisplayName("Completion")
    public void Completion() throws ECRHubException {
        // Completion
        CompletionRequest request = new CompletionRequest();
        request.setApp_id(APP_ID);
        request.setOrig_merchant_order_no("O1695032342508");
        request.setMerchant_order_no("O" + System.currentTimeMillis());
        request.setOrder_amount("10");

        // Setting read timeout,the timeout set here is valid for this request
        ECRHubConfig requestConfig = new ECRHubConfig();
        requestConfig.getSerialPortConfig().setReadTimeout(5 * 60 * 1000);
        request.setConfig(requestConfig);

        // Execute completion request
        System.out.println("Completion request:" + request);
        CompletionResponse response = client.execute(request);
        System.out.println("Completion response:" + response);
    }

    @Test
    @DisplayName("Void")
    public void Void() throws ECRHubException {
        VoidRequest request = new VoidRequest();
        request.setApp_id(APP_ID);
        request.setOrig_merchant_order_no("O1695032342508");
        request.setMerchant_order_no("O" + System.currentTimeMillis());

        System.out.println("Void request:" + request);
        VoidResponse response = client.execute(request);
        System.out.println("Void response:" + response);
    }

    @Test
    @DisplayName("Refund")
    public void Refund() throws ECRHubException {
        RefundRequest request = new RefundRequest();
        request.setApp_id(APP_ID);
        request.setOrig_merchant_order_no("O1695032342508");
        request.setMerchant_order_no("O" + System.currentTimeMillis());
        request.setOrder_amount("1");

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