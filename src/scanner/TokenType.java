package scanner;

public enum TokenType {
	// Keywords
	T_INT, T_STRING, T_OBJECT, T_VOID, T_INHERIT, T_NULL, T_RETURN,

	// Literals
	T_INTEGER_LITERAL, T_STRING_LITERAL,

	// Operators
	T_PLUS, T_MINUS, T_DIVIDE, T_MULTIPLY, T_ARROW,
	
	// Assignment
	T_EQUALS,

	// Punctuation
	T_SEMICOLON, T_LEFT_PAREN, T_RIGHT_PAREN, T_LEFT_BRACE, T_RIGHT_BRACE, T_COMMA,

	// Identifiers
	T_IDENTIFIER,

	// Special
	T_EOF
}
