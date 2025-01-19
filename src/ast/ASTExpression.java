package ast;

import scanner.Token;

public abstract class ASTExpression extends ASTNode {
	public ASTExpression(Token token) {
		super(token);
	}

	@Override
	public abstract String toString();
}
