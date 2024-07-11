package vn.fpt.diamond_shop.constant;

public enum EDiamondColor {
    D,
    E,
    F,
    G,
    H,
    I,
    J,
    K,
    L,
    M,
    N,
    O,
    P,
    Q,
    R,
    S,
    T,
    U,
    V,
    W,
    X,
    Y,
    Z;

    public static EDiamondColor fromValue(String value) {
        for (EDiamondColor color : EDiamondColor.values()) {
            if (color.name().equals(value)) {
                return color;
            }
        }
        return null;
    }
}
