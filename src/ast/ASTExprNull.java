package ast;

public class ASTExprNull extends ASTExpression {
    // Constructor
    public ASTExprNull(int line, int column) {
        super(line, column);
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("ASTExprNull(line=%d, column=%d)", line(), column());
    }
}
