package ast;

public abstract class ASTExpression extends ASTNode {
    // Constructor
    public ASTExpression(int line, int column) {
        super(line, column);
    }

    // Abstract method for specific expression details
    @Override
    public abstract String toString();
}
