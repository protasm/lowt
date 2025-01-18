package ast;

public class ASTStmtExpressionStatement extends ASTStatement {
    private final ASTExpression expression; // The expression in the statement

    // Constructor
    public ASTStmtExpressionStatement(int line, int column, ASTExpression expression) {
        super(line, column);
        this.expression = expression;
    }

    // Accessor
    public ASTExpression expression() {
        return expression;
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("ASTStmtExpressionStatement(expression=%s, line=%d, column=%d)", 
                             expression, line(), column());
    }
}
