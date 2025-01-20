package scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class ScannerOld implements Iterator<Token> {
	private final String source;
	private final TokenList tokens;
	private int start = 0; // Start of the current lexeme
	private int current = 0; // Current position in the source string
	private int line = 1; // Current line in the source string

	// Constructor
	public ScannerOld(String source) {
		this.source = source;
		this.tokens = new TokenList();
	}

	// Main entry point to scan all tokens
	public TokenList scan() {
		while (!isAtEnd()) {
			start = current;

			scanToken();
		}

		tokens.add(new Token(TokenType.T_EOF, "", null, line, current));

		return tokens;
	}

	// Iterator interface: Returns true if there are more tokens
	@Override
	public boolean hasNext() {
		return current < tokens.size();
	}

	// Iterator interface: Returns the next token
	@Override
	public Token next() {
		if (!hasNext()) {
			throw new IllegalStateException("No more tokens");
		}
		return tokens.get(current++);
	}

	// Helper: Scan a single token
	private void scanToken() {
		char c = advance();

		switch (c) {
			case '+':
				addToken(TokenType.T_PLUS);
				break;
			case '-':
				addToken(TokenType.T_MINUS);
				break;
			case '*':
				addToken(TokenType.T_MULTIPLY);
				break;
			case '/':
				addToken(TokenType.T_DIVIDE);
				break;
			case '(':
				addToken(TokenType.T_LEFT_PAREN);
				break;
			case ')':
				addToken(TokenType.T_RIGHT_PAREN);
				break;
			case '{':
				addToken(TokenType.T_LEFT_BRACE);
				break;
			case '}':
				addToken(TokenType.T_RIGHT_BRACE);
				break;
			case ';':
				addToken(TokenType.T_SEMICOLON);
				break;
			case ',':
				addToken(TokenType.T_COMMA);
				break;
			case '"':
				scanString(); // Handle string literal
				break;
			case '=':
				addToken(TokenType.T_EQUALS);
				break;
			case ' ':
			case '\r':
			case '\t':
				// Ignore whitespace
				break;
			case '\n':
				line++; // Handle newlines for line tracking
				break;
			default:
				if (isDigit(c)) {
					scanNumber();
				} else if (isAlpha(c)) {
					scanIdentifier();
				} else {
					// Handle unexpected characters
					throw new IllegalArgumentException("Unexpected character: " + c);
				}
		}
	}

	// Advance and return the current character
	private char advance() {
		return source.charAt(current++);
	}

	// Add a token without a literal value
	private void addToken(TokenType type) {
		addToken(type, null);
	}

	// Add a token with a literal value
	private void addToken(TokenType type, Object literal) {
		String lexeme = source.substring(start, current);
		tokens.add(new Token(type, lexeme, literal, line, start));
	}

	// Scan a number literal
	private void scanNumber() {
		while (isDigit(peek())) {
			advance();
		}

		// Add the number token
		String lexeme = source.substring(start, current);
		addToken(TokenType.T_INTEGER_LITERAL, Integer.parseInt(lexeme));
	}

	// Scan an identifier or keyword
	private void scanIdentifier() {
		while (isAlphaNumeric(peek())) {
			advance();
		}

		String lexeme = source.substring(start, current);
		TokenType type = getKeywordType(lexeme);
		addToken(type != null ? type : TokenType.T_IDENTIFIER);
	}

	private void scanString() {
		StringBuilder value = new StringBuilder();

		// Collect characters until the closing quote or end of file
		while (!isAtEnd() && peek() != '"') {
			if (peek() == '\n') {
				line++; // Handle multi-line strings by tracking newlines
			}

			value.append(advance());
		}

		// Check for a closing quote
		if (isAtEnd()) {
			throw new IllegalArgumentException("Unterminated string literal at line " + line);
		}

		// Consume the closing quote
		advance();

		// Add the string token
		addToken(TokenType.T_STRING_LITERAL, value.toString());
	}

	// Get token type for a keyword
	private TokenType getKeywordType(String lexeme) {
		switch (lexeme) {
			case "int":
				return TokenType.T_INT;
			case "string":
				return TokenType.T_STRING;
			case "object":
				return TokenType.T_OBJECT;
			case "void":
				return TokenType.T_VOID;
			case "inherit":
				return TokenType.T_INHERIT;
			case "null":
				return TokenType.T_NULL;
			case "return":
				return TokenType.T_RETURN;
			default:
				return null;
		}
	}

	// Peek at the current character without advancing
	private char peek() {
		return isAtEnd() ? '\0' : source.charAt(current);
	}

	// Check if a character is a digit
	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	// Check if a character is alphabetic
	private boolean isAlpha(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	// Check if a character is alphanumeric
	private boolean isAlphaNumeric(char c) {
		return isAlpha(c) || isDigit(c);
	}

	// Check if we've reached the end of the source
	private boolean isAtEnd() {
		return current >= source.length();
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java Scanner <file>");

			System.exit(1);
		}

		String fileName = args[0];
		Path filePath = Paths.get(fileName);
		String source;

		try {
			source = Files.readString(filePath);
		} catch (IOException e) {
			System.err.println("Error: Unable to locate or read file '" + fileName + "'");
			System.exit(1);
			return; // Unreachable, but included for clarity
		}

		// Create a Scanner and scan the tokens
		ScannerOld scanner = new ScannerOld(source);
		TokenList tokens = scanner.scan();

		// Print tokens grouped by lines
		int currentLine = -1;
		StringBuilder lineBuffer = new StringBuilder();

		for (int i = 0; i < tokens.size(); i++) {
			Token token = tokens.get(i);

			// Check if we're starting a new line
			if (token.line() != currentLine) {
				// Print the buffered line if moving to a new line
				if (lineBuffer.length() > 0) {
					System.out.println(lineBuffer.toString());
					lineBuffer.setLength(0); // Clear the buffer
				}

				// Update current line
				currentLine = token.line();
			}

			// Append the token to the current line
			lineBuffer.append(token).append(" ");
		}

		// Print the final line if there's anything left in the buffer
		if (lineBuffer.length() > 0) {
			System.out.println(lineBuffer.toString());
		}
	}
}
