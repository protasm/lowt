package ast;

import common.LTType;

public class ASTField {
    private final LTType type;           // The data type of the field
    private final String name;           // The name of the field
    private final ASTExpression initializer; // Optional initializer for the field

    // Constructor
    public ASTField(LTType type, String name, ASTExpression initializer) {
        this.type = type;
        this.name = name;
        this.initializer = initializer;
    }

    // Accessors
    public LTType type() {
        return type;
    }

    public String name() {
        return name;
    }

    public ASTExpression initializer() {
        return initializer;
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("ASTField(type=%s, name=%s, initializer=%s)", type, name, initializer);
    }
}
