package ast;

public class ASTStmtCall extends ASTStatement {
    private final ASTExprCall call; // The method call being executed as a statement

    // Constructor
    public ASTStmtCall(int line, int column, ASTExprCall call) {
        super(line, column);
        this.call = call;
    }

    // Accessor
    public ASTExprCall methodCall() {
        return call;
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("ASTStmtCall(call=%s, line=%d, column=%d)", 
                             call, line(), column());
    }
}
