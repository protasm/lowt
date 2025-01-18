package ast;

import java.util.List;

public class ASTStmtBlock extends ASTStatement {
    private final List<ASTStatement> statements; // The list of statements in the block

    // Constructor
    public ASTStmtBlock(int line, int column, List<ASTStatement> statements) {
        super(line, column);
        this.statements = statements;
    }

    // Accessor
    public List<ASTStatement> statements() {
        return statements;
    }

    // String representation for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("ASTStmtBlock(line=%d, column=%d)\n", line(), column()));
        
        for (ASTStatement stmt : statements)
            sb.append("  ").append(stmt).append("\n");

        return sb.toString().trim();
    }
}
