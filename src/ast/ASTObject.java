package ast;

import java.util.List;

public class ASTObject {
    private final String name;               // The name of the object
    private final List<ASTField> fields;      // List of fields in the object
    private final List<ASTMethod> methods;    // List of methods in the object
    private final String parent;             // Parent object (for inheritance, nullable)

    // Constructor
    public ASTObject(String name, List<ASTField> fields, List<ASTMethod> methods, String parent) {
        this.name = name;
        this.fields = fields;
        this.methods = methods;
        this.parent = parent;
    }

    // Accessors
    public String name() {
        return name;
    }

    public List<ASTField> fields() {
        return fields;
    }

    public List<ASTMethod> methods() {
        return methods;
    }

    public String parent() {
        return parent;
    }

    // String representation for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("ASTObject(name=").append(name);
        
        if (parent != null) sb.append(", parent=").append(parent);
        
        sb.append(")\nFields:\n");
        
        for (ASTField field : fields)
            sb.append("  ").append(field).append("\n");

        sb.append("Methods:\n");
        
        for (ASTMethod method : methods)
            sb.append("  ").append(method).append("\n");

        return sb.toString().trim();
    }
}
