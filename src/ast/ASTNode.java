package ast;

import org.objectweb.asm.MethodVisitor;

import scanner.Token;

public abstract class ASTNode {
	protected final int line;
	protected final int column;

	public ASTNode(Token startToken) {
		this.line = startToken.line();
		this.column = startToken.column();
	}

	public int line() {
		return line;
	}

	public int column() {
		return column;
	}

	public void toBytecode(MethodVisitor mv) {
		throw new UnsupportedOperationException("toBytecode() not implemented for " + this.getClass().getSimpleName());
	}

	@Override
	public String toString() {
		return String.format("%s", this.getClass().getSimpleName());
	}
}
