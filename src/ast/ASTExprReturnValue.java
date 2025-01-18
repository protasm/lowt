package ast;

import scanner.Token;

public class ASTExprReturnValue extends ASTExpression {
    private final ASTExpression value; // The value to return (could be null)

    // Constructor
    public ASTExprReturnValue(Token token, ASTExpression value) {
        super(token.line(), token.column());
        
        this.value = value;
    }

    // Accessor
    public ASTExpression returnValue() {
        return value;
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("ASTExprReturnValue(value=%s, line=%d, column=%d)", 
                             value, line(), column());
    }
}
