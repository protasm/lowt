package parser.parselet;

import parser.Parser;

public interface Parselet {
    void parse(Parser parser, boolean canAssign);
}
