package ast;

import scanner.Token;

public class ASTStmtInvocation extends ASTStatement {
	private final ASTExprInvocation invocation;

	public ASTStmtInvocation(Token token, ASTExprInvocation invocation) {
		super(token);
		
		this.invocation = invocation;
	}

	public ASTExprInvocation methodInvocation() {
		return invocation;
	}

	@Override
	public String toString() {
		return String.format("ASTStmtInvocation(invocation=%s)", invocation);
	}
}
