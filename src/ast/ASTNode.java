package ast;

public abstract class ASTNode {
    private final int line;   // The line number in the source code
    private final int column; // The column number in the source code

    // Constructor
    public ASTNode(int line, int column) {
        this.line = line;
        this.column = column;
    }

    // Accessors
    public int line() {
        return line;
    }

    public int column() {
        return column;
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("%s(line=%d, column=%d)", this.getClass().getSimpleName(), line, column);
    }
}
