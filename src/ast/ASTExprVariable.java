package ast;

import scanner.Token;

public class ASTExprVariable extends ASTExpression {
	private final String name;

	public ASTExprVariable(Token token) {
		super(token);

		this.name = token.lexeme();
	}

	public String name() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("ASTExprVariable(name=%s)", name);
	}
}
