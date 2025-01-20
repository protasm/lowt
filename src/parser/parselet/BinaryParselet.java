package parser.parselet;

import io.github.protasm.lpc2j.BinaryOpType;
import parser.ParseRule;
import parser.Parser;
import scanner.TokenType;

public class BinaryParselet implements Parselet {
    @Override
    public void parse(Parser parser, boolean canAssign) {
		TokenType operatorType = parser.previous().type();
		ParseRule rule = parser.getRule(operatorType);
	
		// evaluate and load RHS operand
		parser.parsePrecedence(rule.precedence() + 1, true);
	
		switch (operatorType) {
		case TOKEN_PLUS:
		    compiler.binaryOp(BinaryOpType.BOP_ADD);
	
		    break;
		case TOKEN_MINUS:
		    compiler.binaryOp(BinaryOpType.BOP_SUB);
	
		    break;
		case TOKEN_STAR:
		    compiler.binaryOp(BinaryOpType.BOP_MULT);
	
		    break;
		case TOKEN_SLASH:
		    compiler.binaryOp(BinaryOpType.BOP_DIV);
	
		    break;
		default:
		    return;
		} // switch (operatorType)
    }
}