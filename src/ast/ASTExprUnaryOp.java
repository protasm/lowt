package ast;

public class ASTExprUnaryOp extends ASTExpression {
    public enum Operator {
        NEGATE, NOT
    }

    private final ASTExpression operand; // The operand for the unary operation
    private final Operator operator;    // The unary operator (e.g., NEGATE, NOT)

    // Constructor
    public ASTExprUnaryOp(int line, int column, ASTExpression operand, Operator operator) {
        super(line, column);
        this.operand = operand;
        this.operator = operator;
    }

    // Accessors
    public ASTExpression operand() {
        return operand;
    }

    public Operator operator() {
        return operator;
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("ASTExprUnaryOp(operator=%s, operand=%s, line=%d, column=%d)", 
                             operator, operand, line(), column());
    }
}
