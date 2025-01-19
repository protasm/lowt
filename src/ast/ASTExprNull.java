package ast;

import scanner.Token;

public class ASTExprNull extends ASTExpression {
	// Constructor
	public ASTExprNull(Token token) {
		super(token);
	}

	// String representation for debugging
	@Override
	public String toString() {
		return "ASTExprNull";
	}
}
