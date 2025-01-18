package ast;

public class ASTStmtInvocation extends ASTStatement {
    private final ASTExprInvocation invocation; // The method invocation being executed as a statement

    // Constructor
    public ASTStmtInvocation(int line, int column, ASTExprInvocation invocation) {
        super(line, column);
        this.invocation = invocation;
    }

    // Accessor
    public ASTExprInvocation methodInvocation() {
        return invocation;
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("ASTStmtInvocation(invocation=%s, line=%d, column=%d)", 
                             invocation, line(), column());
    }
}
