package com.nuclearthinking.arcanoid;

/**
 * Date: 20.03.2016
 * Time: 21:44
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public enum BrickType {
    BRICK1(1),
    BRICK2(2),
    BRICK3(3),
    BRICK4(4),
    BRICK5(5),
    BRICK6(6);

    final int typeId;

    BrickType(int i) {
        typeId = i;
    }

    public static BrickType byId(final int i) {
        switch (i) {
            case 1:
                return BRICK1;
            case 2:
                return BRICK2;
            case 3:
                return BRICK3;
            case 4:
                return BRICK4;
            case 5:
                return BRICK5;
            case 6:
                return BRICK6;
            default:
                return BRICK1;
        }
    }
}
