package ast;

import scanner.Token;

public class ASTStmtAssignment extends ASTStatement {
	private final String variable; // The variable being assigned to
	private final ASTExpression value; // The value being assigned to the variable

	// Constructor
	public ASTStmtAssignment(Token token, String variable, ASTExpression value) {
		super(token);
		
		this.variable = variable;
		this.value = value;
	}

	// Accessors
	public String variable() {
		return variable;
	}

	public ASTExpression value() {
		return value;
	}

	// String representation for debugging
	@Override
	public String toString() {
		return String.format("ASTStmtAssignment(variable=%s, value=%s)", variable, value);
	}
}
