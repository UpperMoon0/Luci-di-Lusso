package vn.fpt.diamond_shop.constant;

import lombok.Getter;

@Getter
public enum EUserRole {
    MANAGER("ROLE_MANAGER"),
    DELIVERY_STAFF("ROLE_DELIVERY_STAFF"),
    CUSTOMER("ROLE_CUSTOMER");

    private final String value;

    EUserRole(String value) { this.value = value; }
}
