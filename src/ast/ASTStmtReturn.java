package ast;

import scanner.Token;

public class ASTStmtReturn extends ASTStatement {
	private final ASTExprReturnValue value;

	public ASTStmtReturn(Token token, ASTExprReturnValue value) {
		super(token);

		this.value = value;
	}

	public ASTExprReturnValue returnValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.format("ASTStmtReturn(returnValue=%s)", value);
	}
}
