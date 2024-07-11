package vn.fpt.diamond_shop.constant;

public enum EDiamondClarity {
    FL,
    IF,
    VVS1,
    VVS2,
    VS1,
    VS2,
    SI1,
    SI2,
    I1,
    I2,
    I3;

    public static EDiamondClarity fromValue(String value) {
        for (EDiamondClarity clarity : EDiamondClarity.values()) {
            if (clarity.name().equals(value)) {
                return clarity;
            }
        }
        return null;
    }
}
