package vn.fpt.diamond_shop.constant;

import lombok.Getter;

@Getter
public enum EDiamondCut {
    EXCELLENT("Excellent"),
    VERY_GOOD("Very Good"),
    GOOD("Good"),
    FAIR("Fair"),
    POOR("Poor");

    private final String value;

    EDiamondCut(String value) {
        this.value = value;
    }

    public static EDiamondCut fromValue(String value) {
        for (EDiamondCut cut : EDiamondCut.values()) {
            if (cut.getValue().equals(value)) {
                return cut;
            }
        }
        return null;
    }
}