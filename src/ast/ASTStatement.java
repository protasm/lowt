package ast;

public abstract class ASTStatement extends ASTNode {
    // Constructor
    public ASTStatement(int line, int column) {
        super(line, column);
    }

    // Abstract method for specific statement details
    @Override
    public abstract String toString();
}
