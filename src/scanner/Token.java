package scanner;

public class Token {
	private final TokenType type;
	private final String lexeme; // The exact text of the token
	private final Object literal; // Literal value (e.g., Integer, String)
	private final int line; // Line number in the source file
	private final int column; // Column number in the source file

	// Constructor
	public Token(TokenType type, String lexeme, Object literal, int line, int column) {
		this.type = type;
		this.lexeme = lexeme;
		this.literal = literal;
		this.line = line;
		this.column = column;
	}

	// Accessors without the "get" prefix
	public TokenType type() {
		return type;
	}

	public String lexeme() {
		return lexeme;
	}

	public Object literal() {
		return literal;
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
		return String.format("(%s)", type);
	}
}
