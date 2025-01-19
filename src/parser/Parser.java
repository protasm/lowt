package parser;

import ast.*;
import scanner.Scanner;
import scanner.Token;
import scanner.TokenType;

import static scanner.TokenType.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {
	private final List<Token> tokens;
	private int currentIndex = 0;

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
	}

	private Token current() {
		return tokens.get(currentIndex);
	}

	private Token previous() {
		return tokens.get(currentIndex - 1);
	}

	private void advance() {
		currentIndex++;
	}

	private Token consume(TokenType type, String msg) {
		if (match(type))
			return previous();

		throw new ParseException(msg, current());
	}

	private Token consume(TokenType[] types, String msg) {
		for (TokenType type : types)
			if (match(type))
				return previous();

		throw new ParseException(msg, current());
	}

	private boolean check(TokenType type) {
		return current().type() == type;
	}

	private boolean match(TokenType type) {
		if (check(type)) {
			advance();

			return true;
		}

		return false;
	}

	private boolean isAtEnd() {
		return currentIndex >= tokens.size() || current().type() == T_EOF;
	}

	public ASTObject object(String name) {
		String parent = null;

		if (match(T_INHERIT))
			parent = inherit();

		ASTObject object = new ASTObject(parent, name);

		while (!isAtEnd())
			property(object);

		return object;
	}

	private String inherit() {
		consume(T_STRING_LITERAL, "Expected string after 'inherit'.");

 		return previous().lexeme();
	}

	private void property(ASTObject object) {
		Token typeToken = consume(new TokenType[] { T_VOID, T_INT, T_STRING, T_OBJECT }, "Expected property type.");
		Token nameToken = consume(T_IDENTIFIER, "Expected property name.");

		if (match(T_LEFT_PAREN))
			method(object, typeToken, nameToken);
		else
			field(object, typeToken, nameToken);
	}

	private void field(ASTObject object, Token typeToken, Token nameToken) {
		ASTExpression initializer = null;

		if (match(T_EQUALS))
			initializer = expression(current());

		consume(T_SEMICOLON, "Expected ';' after field declaration.");

		ASTField field = new ASTField(typeToken, nameToken, initializer);

		object.fields().add(field);
	}

	private void method(ASTObject object, Token typeToken, Token nameToken) {
		List<ASTParameter> parameters = parameters();

		consume(T_LEFT_BRACE, "Expected '{' after method declaration.");

		ASTStmtBlock body = block(current());
		ASTMethod method = new ASTMethod(typeToken, nameToken, parameters, body);

		object.methods().add(method);
	}

	private List<ASTParameter> parameters() {
	    List<ASTParameter> params = new ArrayList<>();

	    if (match(T_RIGHT_PAREN)) // No parameters
	        return params;

	    do {
	        Token typeToken = consume(new TokenType[] { T_INT, T_STRING, T_OBJECT }, "Expected parameter type.");
	        Token nameToken = consume(T_IDENTIFIER, "Expected parameter name.");

	        params.add(new ASTParameter(typeToken, nameToken));
	    } while (match(T_COMMA));

	    consume(T_RIGHT_PAREN, "Expected ')' after parameter list.");

	    return params;
	}

	private ASTStmtBlock block(Token token) {
	    List<ASTStatement> statements = new ArrayList<>();

	    while (!check(T_RIGHT_BRACE) && !isAtEnd())
	        statements.add(statement());

	    consume(T_RIGHT_BRACE, "Expected '}' after method body.");

	    return new ASTStmtBlock(token, statements);
	}

	public ASTStatement statement() {
		if (match(T_RETURN))
			return returnStatement(current());
		else
	        return expressionStatement(current());
	}

	private ASTStmtReturn returnStatement(Token startToken) {
		ASTExpression expr = null;

		if (!check(T_SEMICOLON))
			expr = expression(current());

		consume(T_SEMICOLON, "Expected ';' after return statement.");
		
		ASTExprReturnValue returnValue = new ASTExprReturnValue(current(), expr);

		return new ASTStmtReturn(startToken, returnValue);
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
		if (match(T_IDENTIFIER))
			return new ASTExprVariable(previous());
		else if (match(T_INTEGER_LITERAL))
			return new ASTExprIntegerLiteral(previous());
		else if (match(T_STRING_LITERAL))
			return new ASTExprStringLiteral(previous());

		throw new ParseException("Unexpected token for primary expression.", current());
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

            Scanner scanner = new Scanner(source);
            List<Token> tokens = scanner.scan();

            Parser parser = new Parser(tokens);
            ASTObject ast = parser.object(fileName);

            System.out.println(ast);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
