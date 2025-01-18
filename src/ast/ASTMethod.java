package ast;

import common.LTType;
import java.util.List;

public class ASTMethod {
    private final LTType returnType;         // The return type of the method
    private final String name;              // The name of the method
    private final List<ASTParameter> parameters; // The list of parameters
    private final List<ASTStatement> body;   // The list of statements in the method body

    // Constructor
    public ASTMethod(LTType returnType, String name, List<ASTParameter> parameters, List<ASTStatement> body) {
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    // Accessors
    public LTType returnType() {
        return returnType;
    }

    public String name() {
        return name;
    }

    public List<ASTParameter> parameters() {
        return parameters;
    }

    public List<ASTStatement> body() {
        return body;
    }

    // String representation for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ASTMethod(returnType=%s, name=%s)\n", returnType, name));
        sb.append("Parameters:\n");
        for (ASTParameter param : parameters) {
            sb.append("  ").append(param).append("\n");
        }
        sb.append("Body:\n");
        for (ASTStatement stmt : body) {
            sb.append("  ").append(stmt).append("\n");
        }
        return sb.toString().trim();
    }
}
