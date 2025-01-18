package ast;

import scanner.Token;

public class ASTArgument extends ASTNode {
    private final ASTExpression expression;  // The expression passed as an argument

    // Constructor
    public ASTArgument(Token token, ASTExpression expression) {
        super(token.line(), token.column());
        
        this.expression = expression;
    }

    // Accessor for the argument expression
    public ASTExpression getExpression() {
        return expression;
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("ASTArgument(expression=%s, line=%d, column=%d)", expression, line(), column());
    }
}
