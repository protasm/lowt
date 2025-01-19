package ast;

import scanner.Token;

public class ASTExprReturnValue extends ASTExpression {
	private final ASTExpression value;

	public ASTExprReturnValue(Token token, ASTExpression value) {
		super(token);

		this.value = value;
	}

	public ASTExpression returnValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.format("ASTExprReturnValue(value=%s)", value);
	}
}
