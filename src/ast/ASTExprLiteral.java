package ast;

import scanner.Token;

public class ASTExprLiteral extends ASTExpression {
    private final Object value;

    public ASTExprLiteral(Token token) {
        super(token.line(), token.column());
        
        this.value = token.literal();
    }

    public Object value() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("ASTLiteral(value=%s, line=%d, column=%d)", value, line(), column());
    }
}
