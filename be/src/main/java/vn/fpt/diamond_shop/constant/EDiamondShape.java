package vn.fpt.diamond_shop.constant;

import lombok.Getter;

@Getter
public enum EDiamondShape {
    ROUND("Round"),
    PRINCESS("Princess"),
    OVAL("Oval"),
    MARQUISE("Marquise"),
    HEART("Heart"),
    EMERALD("Emerald"),
    PEAR("Pear"),
    ASSHER("Assher"),
    CUSHION("Cushion"),
    RADIANT("Radiant");

    private final String value;

    EDiamondShape(String value) {
        this.value = value;
    }
}