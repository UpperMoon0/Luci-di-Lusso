package vn.fpt.diamond_shop.constant;

import lombok.Getter;

@Getter
public enum EJewelryType {
    RING("Ring"),
    NECKLACE("Necklace"),
    BRACELET("Bracelet"),
    EARRING("Earring"),
    PENDANT("Pendant"),
    BROOCH("Brooch");

    private final String value;

    EJewelryType(String value) {
        this.value = value;
    }
}