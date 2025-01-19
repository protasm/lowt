package ast;

import scanner.Token;

public class ASTExprStringLiteral extends ASTExpression {
	private final String value;

	public ASTExprStringLiteral(Token token) {
		super(token);

		this.value = token.lexeme();
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return String.format("ASTExprStringLiteral(value=%s)", value);
	}
}
