package ast;

import scanner.Token;

public class ASTExprUnaryOp extends ASTExpression {
	public enum Operator {
		NEGATE, NOT
	}

	private final ASTExpression operand; // The operand for the unary operation
	private final Operator operator; // The unary operator (e.g., NEGATE, NOT)

	// Constructor
	public ASTExprUnaryOp(Token token, ASTExpression operand, Operator operator) {
		super(token);

		this.operand = operand;
		this.operator = operator;
	}

	// Accessors
	public ASTExpression operand() {
		return operand;
	}

	public Operator operator() {
		return operator;
	}

	// String representation for debugging
	@Override
	public String toString() {
		return String.format("ASTExprUnaryOp(operator=%s, operand=%s)", operator, operand);
	}
}
