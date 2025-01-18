package ast;

import scanner.Token;

public class ASTStmtReturn extends ASTStatement {
    private final ASTExprReturnValue value; // The return value (if any)

    // Constructor
    public ASTStmtReturn(Token token, ASTExprReturnValue value) {
        super(token.line(), token.column());
        
        this.value = value;
    }

    // Accessor
    public ASTExprReturnValue returnValue() {
        return value;
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("ASTStmtReturn(returnValue=%s, line=%d, column=%d)", 
                             value, line(), column());
    }
}
