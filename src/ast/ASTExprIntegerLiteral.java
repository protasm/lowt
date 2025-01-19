package ast;

import scanner.Token;

public class ASTExprIntegerLiteral extends ASTExpression {
	private final Integer value;

	public ASTExprIntegerLiteral(Token token) {
		super(token);

		this.value = (Integer) token.literal();
	}

	public Integer value() {
		return value;
	}

	@Override
	public String toString() {
		return String.format("ASTExprIntegerLiteral(value=%s)", value);
	}
}
