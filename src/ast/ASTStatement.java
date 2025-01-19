package ast;

import scanner.Token;

public abstract class ASTStatement extends ASTNode {
	// Constructor
	public ASTStatement(Token token) {
		super(token);
	}

	// Abstract method for specific statement details
	@Override
	public abstract String toString();
}
