package com.codepay.register.sdk.enums;

public enum ETransType {
    /**
     * Purchase/Sale
     */
    SALE("1"),
    /**
     * Purchase Cancel/Void
     */
    VOID("2"),
    /**
     * Refund/Return
     */
    REFUND("3"),
    /**
     * Authorization
     */
    AUTHORIZATION("4"),
    /**
     * completion
     */
    COMPLETION("6"),
    /**
     * Cashback
     */
    CASH_BACK("11");

    ETransType(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public static ETransType codeOf(String code) {
        if (code != null) {
            for (ETransType item : values()) {
                if (item.getCode().equals(code)) {
                    return item;
                }
            }
        }
        return null;
    }
}