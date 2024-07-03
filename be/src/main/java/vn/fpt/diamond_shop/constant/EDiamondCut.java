package vn.fpt.diamond_shop.constant;

import lombok.Getter;

@Getter
public enum EDiamondCut {
    IDEAL("Ideal"),
    EXCELLENT("Excellent"),
    VERY_GOOD("Very Good"),
    GOOD("Good"),
    FAIR("Fair"),
    POOR("Poor");

    private final String value;

    EDiamondCut(String value) {
        this.value = value;
    }
}