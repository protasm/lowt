package ast;

import scanner.Token;

public class ASTExprVariable extends ASTExpression {
    private final String name;

    public ASTExprVariable(Token token) {
        super(token.line(), token.column());
        
        this.name = token.lexeme();
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("ASTExprVariable(name=%s, line=%d, column=%d)", name, line(), column());
    }
}
