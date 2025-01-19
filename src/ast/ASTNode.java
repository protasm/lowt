package ast;

import scanner.Token;

public abstract class ASTNode {
	protected final int line;
	protected final int column;

	public ASTNode(Token token) {
		this.line = token.line();
		this.column = token.column();
	}

	public int line() {
		return line;
	}

	public int column() {
		return column;
	}

	// String representation for debugging
	@Override
	public String toString() {
		return String.format("%s", this.getClass().getSimpleName());
	}
}
