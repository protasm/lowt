package ast;

public class ASTExprInvocation extends ASTExpression {
    private final String objectName;      // The name of the target object
    private final ASTExprCall call; // The method call being invoked

    // Constructor
    public ASTExprInvocation(int line, int column, String objectName, ASTExprCall call) {
        super(line, column);
        this.objectName = objectName;
        this.call = call;
    }

    // Accessors
    public String objectName() {
        return objectName;
    }

    public ASTExprCall methodCall() {
        return call;
    }

    // String representation for debugging
    @Override
    public String toString() {
        return String.format("ASTExprInvocation(objectName=%s, call=%s, line=%d, column=%d)", 
                             objectName, call, line(), column());
    }
}
