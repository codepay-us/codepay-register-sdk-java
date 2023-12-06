package com.codepay.register.sdk.sp.serialport;

public interface SerialPortMessageHandler {

    void handle(byte[] message);

}