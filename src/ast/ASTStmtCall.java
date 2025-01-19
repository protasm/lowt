package ast;

import scanner.Token;

public class ASTStmtCall extends ASTStatement {
	private final ASTExprCall call; // The method call being executed as a statement

	// Constructor
	public ASTStmtCall(Token token, ASTExprCall call) {
		super(token);
		
		this.call = call;
	}

	// Accessor
	public ASTExprCall methodCall() {
		return call;
	}

	// String representation for debugging
	@Override
	public String toString() {
		return String.format("ASTStmtCall(call=%s)", call);
	}
}
