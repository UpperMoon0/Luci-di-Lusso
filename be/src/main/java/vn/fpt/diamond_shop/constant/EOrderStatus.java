package vn.fpt.diamond_shop.constant;

import lombok.Getter;

@Getter
public enum EOrderStatus {

    PREPARING("Preparing"),
    DONE("Done");

    private final String value;

    EOrderStatus(String value) { this.value = value; }
}
