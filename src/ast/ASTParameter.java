package ast;

import common.LTType;
import scanner.Token;

public class ASTParameter extends ASTNode {
    private final LTType type;  // The data type of the parameter
    private final String name;  // The name of the parameter

    // Constructor
    public ASTParameter(Token token, LTType type, String name) {
    	super(token.line(), token.column());
    	
        this.type = type;
        this.name = name;
    }

    // Accessors
    public LTType type() {
        return type;
    }

    public String name() {
        return name;
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("ASTParameter(type=%s, name=%s)", type, name, line(), column());
    }
}
