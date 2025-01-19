package ast;

import java.util.ArrayList;
import java.util.List;

public class ASTObject {
	private final String parent; // Parent object (for inheritance, nullable)
	private final String name; // The name of the object
	private final List<ASTField> fields; // List of fields in the object
	private final List<ASTMethod> methods; // List of methods in the object

	public ASTObject(String parent, String name) {
		this.parent = parent;
		this.name = name;
		this.fields = new ArrayList<>();
		this.methods = new ArrayList<>();
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

		if (parent != null)
			sb.append(", parent=").append(parent);

		sb.append(")\n\nFields:\n");

		for (ASTField field : fields)
			sb.append("  ").append(field).append("\n");

		sb.append("\nMethods:\n");

		for (ASTMethod method : methods)
			sb.append(method).append("\n\n");

		return sb.toString().trim();
	}
}
