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
    ASSCHER("Asscher"),
    CUSHION("Cushion"),
    RADIANT("Radiant"),
    TRILLION("Trillion"),
    BAGUETTE("Baguette");

    private final String value;

    EDiamondShape(String value) {
        this.value = value;
    }

    public static EDiamondShape fromValue(String value) {
        for (EDiamondShape shape : EDiamondShape.values()) {
            if (shape.getValue().equals(value)) {
                return shape;
            }
        }
        return null;
    }
}