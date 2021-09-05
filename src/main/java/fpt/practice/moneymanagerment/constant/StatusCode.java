package fpt.practice.moneymanagerment.constant;

import fpt.practice.moneymanagerment.util.PropertiesReader;

public enum StatusCode {

    SUCCESS(0,"Success"),

    ERROR_UNKNOWN(1,"Error Unknown"),

    ACCOUNT_NOT_EXIST(401, PropertiesReader.getProperty(PropertyKeys.ACCOUNT_NOT_EXIST)),
    SPENDING_TYPE_NOT_EXIST(402, PropertiesReader.getProperty(PropertyKeys.SPENDING_TYPE_NOT_EXIST)),
    SUB_SPENDING_TYPE_NOT_EXIST(403, PropertiesReader.getProperty(PropertyKeys.SUB_SPENDING_TYPE_NOT_EXIST)),
    UNIT_NOT_EXIST(405, PropertiesReader.getProperty(PropertyKeys.UNIT_NOT_EXIST)),
    SPENDING_NOT_EXIST(406, PropertiesReader.getProperty(PropertyKeys.SPENDING_NOT_EXIST));

    private Integer status;

    private String message;

    StatusCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
