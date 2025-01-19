package ast;

import scanner.Token;

public class ASTArgument extends ASTNode {
	private final ASTExpression expression;

	public ASTArgument(Token token, ASTExpression expression) {
		super(token);

		this.expression = expression;
	}

	public ASTExpression expression() {
		return expression;
	}

	@Override
	public String toString() {
		return String.format("ASTArgument(expression=%s)", expression);
	}
}
