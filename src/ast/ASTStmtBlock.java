package ast;

import java.util.List;

import scanner.Token;

public class ASTStmtBlock extends ASTStatement {
	private final List<ASTStatement> statements; // The list of statements in the block

	// Constructor
	public ASTStmtBlock(Token token, List<ASTStatement> statements) {
		super(token);

		this.statements = statements;
	}

	// Accessor
	public List<ASTStatement> statements() {
		return statements;
	}

	// String representation for debugging
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("ASTStmtBlock\n");

		for (ASTStatement stmt : statements)
			sb.append(stmt).append("\n");

		return sb.toString().trim();
	}
}
