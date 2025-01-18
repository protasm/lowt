package common;

public enum LTType {
    LT_INT, LT_STRING, LT_OBJECT, LT_VOID;

    @Override
    public String toString() {
        return name().toLowerCase().replace("lt_", ""); // Display without the prefix
    }
}
