package parser;

import parser.parselet.*;

import static parser.Parser.Precedence.*;
import static scanner.TokenType.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ast.*;
import ast.expr.*;
import ast.stmt.*;
import scanner.*;

public class Parser {
	public static final class Precedence {
		public static final int PREC_NONE = 0;
		public static final int PREC_ASSIGNMENT = 1;
		public static final int PREC_OR = 2;
		public static final int PREC_AND = 3;
		public static final int PREC_EQUALITY = 4;
		public static final int PREC_COMPARISON = 5;
		public static final int PREC_TERM = 6;
		public static final int PREC_FACTOR = 7;
		public static final int PREC_UNARY = 8;
		public static final int PREC_INDEX = 9;
		public static final int PREC_CALL = 10;
		public static final int PREC_PRIMARY = 11;

		private Precedence() {
		}
	}

	private final String name;
	private final TokenList tokens;
	private Map<TokenType, ParseRule> tokenTypeToRule;

	public Parser(String name, TokenList tokens) {
		this.name = name;
		this.tokens = tokens;

		tokenTypeToRule = new HashMap<>();

		registerTokenTypesToRules();
	}

	public ASTObject parse() {
		ASTObject object = new ASTObject(inherit(), name);

		while (!tokens.isAtEnd()) {
			property(object);
		}

		return object;
	}

	private String inherit() {
		if (tokens.match(T_INHERIT)) {
			Token parentToken = tokens.consume(T_STRING_LITERAL, "Expected string after 'inherit'.");
			tokens.consume(T_SEMICOLON, "Expected ';' after inherited object path.");

			return (String) parentToken.literal();
		}

		return null;
	}

	private void property(ASTObject object) {
		Token typeToken = tokens.consume(new TokenType[] { T_VOID, T_INT, T_STRING, T_OBJECT },
				"Expected property type.");
		Token nameToken = tokens.consume(T_IDENTIFIER, "Expected property name.");

		if (tokens.match(T_LEFT_PAREN))
			method(object, typeToken, nameToken);
		else
			field(object, typeToken, nameToken);
	}

	private void field(ASTObject object, Token typeToken, Token nameToken) {
		ASTExpression initializer = null;

		if (tokens.match(T_EQUALS))
			initializer = expression(tokens.current());

		tokens.consume(T_SEMICOLON, "Expected ';' after field declaration.");

		ASTField field = new ASTField(typeToken, nameToken, initializer);

		object.fields().add(field);
	}

	private void method(ASTObject object, Token typeToken, Token nameToken) {
		ASTParameters parameters = parameters();

		tokens.consume(T_LEFT_BRACE, "Expected '{' after method declaration.");

		ASTStmtBlock body = block(tokens.current());
		ASTMethod method = new ASTMethod(typeToken, nameToken, parameters, body);

		object.methods().add(method);
	}

	private ASTParameters parameters() {
		ASTParameters parameters = new ASTParameters(tokens.current());

		if (tokens.match(T_RIGHT_PAREN)) // No parameters
			return parameters;

		do {
			Token typeToken = tokens.consume(new TokenType[] { T_INT, T_STRING, T_OBJECT }, "Expected parameter type.");
			Token nameToken = tokens.consume(T_IDENTIFIER, "Expected parameter name.");

			parameters.add(new ASTParameter(typeToken, nameToken));
		} while (tokens.match(T_COMMA));

		tokens.consume(T_RIGHT_PAREN, "Expected ')' after parameter list.");

		return parameters;
	}

	private ASTStmtBlock block(Token token) {
		List<ASTStatement> statements = new ArrayList<>();

		while (!tokens.check(T_RIGHT_BRACE) && !tokens.isAtEnd())
			statements.add(statement());

		tokens.consume(T_RIGHT_BRACE, "Expected '}' after method body.");

		return new ASTStmtBlock(token, statements);
	}

	public ASTStatement statement() {
		if (tokens.match(T_RETURN))
			return returnStatement(tokens.current());
		else
			return expressionStatement(tokens.current());
	}

	private ASTStmtReturn returnStatement(Token startToken) {
		if (tokens.match(T_SEMICOLON))
			return new ASTStmtReturn(startToken, null);

		ASTExpression expr = expression(tokens.current());

		tokens.consume(T_SEMICOLON, "Expected ';' after return statement.");

		return new ASTStmtReturn(startToken, expr);
	}

	private ASTStmtExpressionStatement expressionStatement(Token startToken) {
		return null;
	}

	private ASTExpression expression(Token startToken) {
		// For simplicity, just return an expression node. In a complete parser, this
		// would
		// involve recursive calls for handling operators, parentheses, and other
		// expressions.
		ASTExpression expr = primaryExpression(); // This is just a placeholder for real parsing logic

		return expr;
	}

	private ASTExpression primaryExpression() {
		if (tokens.match(T_IDENTIFIER))
			return new ASTExprVariable(tokens.previous());
		else if (tokens.match(T_INTEGER_LITERAL))
			return new ASTExprIntegerLiteral(tokens.previous());
		else if (tokens.match(T_STRING_LITERAL))
			return new ASTExprStringLiteral(tokens.previous());

		throw new ParseException("Unexpected token for primary expression.", tokens.current());
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java Parser <source-file>");

			System.exit(1);
		}

		String fileName = args[0];
		Path filePath = Paths.get(fileName);
		String source;

		try {
			source = Files.readString(filePath);

			ScannerOld scanner = new ScannerOld(source);
			TokenList tokens = scanner.scan();

			Parser parser = new Parser(fileName, tokens);
			ASTObject ast = parser.parse();

			System.out.println(ast);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void register(TokenType type, Parselet prefix, Parselet infix, int precedence) {
		tokenTypeToRule.put(type, new ParseRule(prefix, infix, precedence));
	}

	public ParseRule getRule(TokenType type) {
		return tokenTypeToRule.get(type);
	}

	private void registerTokenTypesToRules() {
		// Token Type, Prefix Parselet, Infix Parselet, precedence
//	register(TOKEN_MINUS, new UnaryParselet(), new BinaryParselet(), PREC_TERM);

	register(T_PLUS, null, new BinaryParselet(), PREC_TERM);
//	register(TOKEN_STAR, null, new BinaryParselet(), PREC_FACTOR);
//	register(TOKEN_SLASH, null, new BinaryParselet(), PREC_FACTOR);

//	register(TOKEN_INVOKE, null, new InvokeParselet(), PREC_NONE);

//	register(TOKEN_FALSE, new LiteralParselet(), null, PREC_NONE);
//	register(TOKEN_IDENTIFIER, new IdentifierParselet(), null, PREC_NONE);
//	register(TOKEN_NUM_INT, new NumberParselet(), null, PREC_NONE);
//	register(TOKEN_NUM_FLOAT, new NumberParselet(), null, PREC_NONE);
//	register(TOKEN_STRING, new StringParselet(), null, PREC_NONE);
//	register(TOKEN_TRUE, new LiteralParselet(), null, PREC_NONE);

//	register(TOKEN_COLON, null, null, PREC_NONE);
//	register(TOKEN_COMMA, null, null, PREC_NONE);
//	register(TOKEN_ELSE, null, null, PREC_NONE);
//	register(TOKEN_EOF, null, null, PREC_NONE);
//	register(TOKEN_EQUAL, null, null, PREC_NONE);
//	register(TOKEN_ERROR, null, null, PREC_NONE);
//	register(TOKEN_FOR, null, null, PREC_NONE);
//	register(TOKEN_IF, null, null, PREC_NONE);
//	register(TOKEN_INHERIT, null, null, PREC_NONE);
//	register(TOKEN_LEFT_BRACE, null, null, PREC_NONE);
//	register(TOKEN_MINUS_EQUAL, null, null, PREC_NONE);
//	register(TOKEN_MINUS_MINUS, null, null, PREC_NONE);
//	register(TOKEN_PLUS_EQUAL, null, null, PREC_NONE);
//	register(TOKEN_PLUS_PLUS, null, null, PREC_NONE);
//	register(TOKEN_RETURN, null, null, PREC_NONE);
//	register(TOKEN_RIGHT_BRACE, null, null, PREC_NONE);
//	register(TOKEN_RIGHT_BRACKET, null, null, PREC_NONE);
//	register(TOKEN_RIGHT_PAREN, null, null, PREC_NONE);
//	register(TOKEN_SEMICOLON, null, null, PREC_NONE);
//	register(TOKEN_SLASH_EQUAL, null, null, PREC_NONE);
//	register(TOKEN_STAR_EQUAL, null, null, PREC_NONE);
//	register(TOKEN_TYPE, null, null, PREC_NONE);
//	register(TOKEN_WHILE, null, null, PREC_NONE);
	}
}
