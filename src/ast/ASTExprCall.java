package ast;

import java.util.List;

public class ASTExprCall extends ASTExpression {
    private final String methodName;         // Name of the method being called
    private final List<ASTExpression> arguments; // Arguments passed to the method

    // Constructor
    public ASTExprCall(int line, int column, String methodName, List<ASTExpression> arguments) {
        super(line, column);
        this.methodName = methodName;
        this.arguments = arguments;
    }

    // Accessors
    public String methodName() {
        return methodName;
    }

    public List<ASTExpression> arguments() {
        return arguments;
    }

    // String representation for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ASTExprCall(methodName=%s, line=%d, column=%d", methodName, line(), column()));
        sb.append(", arguments=[");
        for (ASTExpression arg : arguments) {
            sb.append(arg).append(", ");
        }
        if (!arguments.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove trailing comma and space
        }
        sb.append("])");
        return sb.toString();
    }
}
