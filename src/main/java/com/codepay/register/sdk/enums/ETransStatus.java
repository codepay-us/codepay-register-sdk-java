package com.codepay.register.sdk.enums;

public enum ETransStatus {
    /**
     * Pre-order status
     */
    PREORDER("9"),
    /**
     * Order has been created or paying or refunding
     */
    CREATED("0"),
    /**
     * Order failed
     */
    CLOSED("1"),
    /**
     * Order successful
     */
    COMPLETED("2"),
    /**
     * Order cancelled
     */
    CANCELLED("3");

    ETransStatus(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public static ETransStatus codeOf(String code) {
        if (code != null) {
            for (ETransStatus item : values()) {
                if (item.getCode().equals(code)) {
                    return item;
                }
            }
        }
        return null;
    }
}