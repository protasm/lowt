package ast.stmt;

import ast.ASTNode;
import scanner.Token;

public abstract class ASTStatement extends ASTNode {
	public ASTStatement(Token startToken) {
		super(startToken);
	}

	@Override
	public abstract String toString();
}
