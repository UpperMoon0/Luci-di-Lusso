package vn.fpt.diamond_shop.constant;

import lombok.Getter;

@Getter
public enum EUserRole {
    ADMIN("Admin"),
    MANAGER("Manager"),
    SALE("Sale"),
    DELIVERY("Delivery"),
    CUSTOMER("Customer");

    private final String value;

    EUserRole(String value) { this.value = value; }
}
