package ast;

import ast.expr.ASTExpression;
import common.LTType;
import scanner.Token;

public class ASTField extends ASTNode {
	private final LTType type;
	private final String name;
	private final ASTExpression initializer;

	public ASTField(Token startToken, Token nameToken, ASTExpression initializer) {
		super(startToken);

		this.type = startToken.type().toLTType();
		this.name = nameToken.lexeme();
		this.initializer = initializer;
	}

	public LTType type() {
		return type;
	}

	public String name() {
		return name;
	}

	public ASTExpression initializer() {
		return initializer;
	}

	@Override
	public String toString() {
		return String.format("ASTField(type=%s, name=%s, initializer=%s", type, name, initializer);
	}
}
