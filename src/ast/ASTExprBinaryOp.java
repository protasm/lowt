package ast;

import scanner.Token;

public class ASTExprBinaryOp extends ASTExpression {
	public enum Operator {
		ADD, SUBTRACT, MULTIPLY, DIVIDE
	}

	private final ASTExpression left; // Left operand
	private final ASTExpression right; // Right operand
	private final Operator operator; // Binary operator

	// Constructor
	public ASTExprBinaryOp(Token token, ASTExpression left, ASTExpression right, Operator operator) {
		super(token);
		
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	// Accessors
	public ASTExpression left() {
		return left;
	}

	public ASTExpression right() {
		return right;
	}

	public Operator operator() {
		return operator;
	}

	// String representation for debugging
	@Override
	public String toString() {
		return String.format("ASTExprBinaryOp(operator=%s, left=%s, right=%s)", operator, left, right);
	}
}
