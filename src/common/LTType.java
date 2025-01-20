package common;

public enum LTType {
	LT_INT, LT_NULL, LT_OBJECT, LT_STATUS, LT_STRING, LT_VOID;

	public String descriptor() {
		switch (this) {
			case LT_INT:
				return "I";
			case LT_OBJECT:
				return "L"; // Generic object type; specific class names may follow
			case LT_STATUS:
				return "Z";
			case LT_STRING:
				return "Ljava/lang/String;";
			case LT_VOID:
				return "V";
			default:
				throw new IllegalArgumentException("Unsupported LTType: " + this);
		}
	}

	@Override
	public String toString() {
		return name().toLowerCase().replace("lt_", "");
	}
}
