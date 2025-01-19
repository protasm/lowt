package parser;

import scanner.Token;

public class ParseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final int line;
	private final int column;

	public ParseException(String message, int line, int column) {
		super(message); // Pass the error message to the RuntimeException constructor

		this.line = line;
		this.column = column;
	}

	public ParseException(String message, Token token) {
		this(message, token.line(), token.column());
	}

	public int line() {
		return line;
	}

	public int column() {
		return column;
	}

	@Override
	public String toString() {
		return String.format("ParseException at line %d, column %d: %s", line, column, getMessage());
	}
}
