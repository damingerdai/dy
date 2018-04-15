package org.aming.dy.enums;

/**
 *
 * @author aming
 * @create 2018-04-03 8:17
 **/
public enum Access {
    RO(1, "ro"), //只读
    RW(2, "rw"); //可读可写

    private int type;
    private String value;

    Access(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public static Access getDatabaseAccess(int type) {
        for (Access access: values()) {
            if (access.type == type) {
                return access;
            }
        }
        throw new IllegalArgumentException("type '" + type + "' is invalid");
    }
}
