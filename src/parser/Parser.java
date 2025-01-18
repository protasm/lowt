package parser;

import ast.*;
import scanner.Token;
import scanner.TokenType;

import java.util.List;

public class Parser {
    private final List<Token> tokens;  // List of tokens provided by the Scanner
    private int currentIndex = 0;      // Current index in the token list

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    // Helper method to look at the current token
    private Token current() {
        return tokens.get(currentIndex);
    }

    // Helper method to move to the next token
    private void advance() {
        currentIndex++;
    }

    // Helper method to check if the current token is of a certain type
    private boolean check(TokenType type) {
        return current().type() == type;
    }

    // Helper method to consume a token if it's of a certain type
    private boolean match(TokenType type) {
        if (check(type)) {
            advance();
            
            return true;
        }
        return false;
    }

    // Start parsing method
    public ASTStatement parseStatement() {
        if (match(TokenType.T_RETURN))
            return parseReturnStatement();
        
        // Add other statement parsing options here (e.g., assignment, block)
        throw new ParseException("Unexpected statement.", current());
    }

    // Parse a return statement
    private ASTStmtReturn parseReturnStatement() {
        ASTExprReturnValue value = null;

        // If there is an expression after 'return', parse it
        if (!check(TokenType.T_SEMICOLON))
            value = parseExpression();

        // Expect a semicolon after the return statement
        if (!match(TokenType.T_SEMICOLON))
            throw new ParseException("Expected ';' after return statement.", current());

        return new ASTStmtReturn(current(), value);
    }

    // Parse an expression (like 'x + 1')
    private ASTExprReturnValue parseExpression() {
        // For simplicity, just return an expression node. In a complete parser, this would
        // involve recursive calls for handling operators, parentheses, and other expressions.
        ASTExpression expr = parsePrimaryExpression();  // This is just a placeholder for real parsing logic
        
        return new ASTExprReturnValue(current(), expr);
    }

 // Parse primary expressions (variables, literals, etc.)
    private ASTExpression parsePrimaryExpression() {
        if (match(TokenType.T_IDENTIFIER)) // If the token is an identifier
            return new ASTExprVariable(current());
        else if (match(TokenType.T_INTEGER_LITERAL))
            return new ASTExprLiteral(current());

        throw new ParseException("Unexpected token for primary expression.", current());
    }
}
