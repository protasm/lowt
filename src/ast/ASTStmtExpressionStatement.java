package ast;

import scanner.Token;

public class ASTStmtExpressionStatement extends ASTStatement {
	private final ASTExpression expression;

	public ASTStmtExpressionStatement(Token token, ASTExpression expression) {
		super(token);
		
		this.expression = expression;
	}

	public ASTExpression expression() {
		return expression;
	}

	// String representation for debugging
	@Override
	public String toString() {
		return String.format("ASTStmtExpressionStatement(expression=%s)", expression);
	}
}
