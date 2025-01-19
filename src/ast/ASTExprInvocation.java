package ast;

import scanner.Token;

public class ASTExprInvocation extends ASTExpression {
	private final String objectName; // The name of the target object
	private final ASTExprCall call; // The method call being invoked

	// Constructor
	public ASTExprInvocation(Token token, String objectName, ASTExprCall call) {
		super(token);
		
		this.objectName = objectName;
		this.call = call;
	}

	// Accessors
	public String objectName() {
		return objectName;
	}

	public ASTExprCall methodCall() {
		return call;
	}

	// String representation for debugging
	@Override
	public String toString() {
		return String.format("ASTExprInvocation(objectName=%s, call=%s)", objectName, call);
	}
}
