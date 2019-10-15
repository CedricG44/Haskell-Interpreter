package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.visitor.Visitor;

/** Lambda calculus expression. */
public abstract class Expression {

  public abstract Expression reduct(final Variable var, final Expression exp);

  public abstract void accept(final Visitor visitor);

  public abstract boolean isApplication();

  public abstract boolean isLambda();

  public abstract boolean isVariable();

  public abstract boolean isConstant();
}
