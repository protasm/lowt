package ast;

import common.LTType;
import scanner.Token;

import java.util.List;

public class ASTMethod extends ASTNode {
	private final LTType returnType; // The return type of the method
	private final String name; // The name of the method
	private final List<ASTParameter> parameters; // The list of parameters
	private final ASTStmtBlock body; // The list of statements in the method body

	// Constructor
	public ASTMethod(Token typeToken, Token nameToken, List<ASTParameter> parameters, ASTStmtBlock body) {
		super(typeToken);
		
		this.returnType = typeToken.type().toLTType();
		this.name = nameToken.lexeme();
		this.parameters = parameters;
		this.body = body;
	}

	// Accessors
	public LTType returnType() {
		return returnType;
	}

	public String name() {
		return name;
	}

	public List<ASTParameter> parameters() {
		return parameters;
	}

	public ASTStmtBlock body() {
		return body;
	}

	// String representation for debugging
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("ASTMethod(returnType=%s, name=%s)\n", returnType, name));

		sb.append("Parameters:\n");

		for (ASTParameter param : parameters)
			sb.append("  ").append(param).append("\n");

		sb.append("Body:\n");

		sb.append(body);
		
		return sb.toString().trim();
	}
}
