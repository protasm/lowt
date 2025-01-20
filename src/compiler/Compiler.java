package compiler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import ast.ASTField;
import ast.ASTMethod;
import ast.ASTObject;
import ast.expr.ASTExpression;
import parser.Parser;
import scanner.ScannerOld;
import scanner.TokenList;

public class Compiler {
	private final ClassWriter classWriter;
	private ASTObject astObject;

	public Compiler(ASTObject astObject) {
		this.astObject = astObject;

		this.classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
	}

	public byte[] compile() {
		classHeader();
		fields();
		constructor();
		methods();

		return classWriter.toByteArray();
	}

	private void classHeader() {
		String className = astObject.name();
		String superClass = superclass();

		classWriter.visit(Opcodes.V23, Opcodes.ACC_SUPER, className, null, superClass, null);
	}

	private String superclass() {
		return astObject.parent() != null ? astObject.parent() : "java/lang/Object";
	}

	private void fields() {
		for (ASTField field : astObject.fields()) {
			FieldVisitor fv = classWriter.visitField(Opcodes.ACC_PRIVATE, field.name(), field.type().descriptor(), null,
					null);

			if (fv != null) {
				fv.visitEnd();
			}
		}
	}

	private void constructor() {
		MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);

		mv.visitCode();

		// Call super constructor
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);

		// Initialize fields
		for (ASTField field : astObject.fields()) {
			if (field.initializer() != null) {
				mv.visitVarInsn(Opcodes.ALOAD, 0); // Load 'this'
				field.initializer().toBytecode(mv); // Generate bytecode for the initializer
				mv.visitFieldInsn(Opcodes.PUTFIELD, astObject.name(), field.name(), field.type().descriptor());
			}
		}

		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0); // Automatically calculated by ASM
		mv.visitEnd();
	}

	private void methods() {
		for (ASTMethod method : astObject.methods()) {
			MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, method.name(),
					method.parameters().descriptor() + method.returnType().descriptor(), null, null);

			mv.visitCode();

			method.toBytecode(mv);

			mv.visitMaxs(0, 0); // Automatically calculated by ASM
			mv.visitEnd();
		}
	}

	private void expression(ASTExpression expression) {
		// TODO
	}

	private void writeToFile(String directory, String prefix, byte[] bytecode) {
		try {
			Path outputPath = Paths.get(directory, prefix + ".class");

			Files.write(outputPath, bytecode);

			System.out.println(prefix + ".class created in " + directory + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java Compiler <source-file>");
			System.exit(1);
		}

		Path filePath = Paths.get(args[0]);
		String directory = filePath.getParent().toString();
		String fileName = filePath.getFileName().toString();
		String prefix = fileName.substring(0, fileName.indexOf('.'));

		try {
			String source = Files.readString(filePath);

			ScannerOld scanner = new ScannerOld(source);
			TokenList tokens = scanner.scan();

			Parser parser = new Parser(prefix, tokens);
			ASTObject ast = parser.parse();

			Compiler compiler = new Compiler(ast);
			byte[] bytes = compiler.compile();

			compiler.writeToFile(directory, prefix, bytes);

			System.out.println("Compilation successful.");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
