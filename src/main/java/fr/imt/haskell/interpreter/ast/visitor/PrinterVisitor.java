package fr.imt.haskell.interpreter.ast.visitor;

import fr.imt.haskell.interpreter.ast.*;

/** Beta-Reduction implementation using AST visitor. */
public class PrinterVisitor implements Visitor {

  public PrinterVisitor() {
    System.out.println("Printing AST...");
  }

  @Override
  public void visit(final Application application) {
    System.out.println("Visited application: " + application);
    application.getExpL().accept(this);
    application.getExpR().accept(this);
  }

  @Override
  public void visit(final Lambda lambda) {
    System.out.println("Visited lambda: " + lambda);
    lambda.getVar().accept(this);
    lambda.getExp().accept(this);
  }

  @Override
  public void visit(final Variable variable) {
    System.out.println("Visited variable: " + variable);
  }

  @Override
  public void visit(final Constant constant) {
    System.out.println("Visited constant: " + constant);
  }
}
