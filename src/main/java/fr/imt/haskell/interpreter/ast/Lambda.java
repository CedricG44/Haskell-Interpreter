package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.visitor.Visitor;

/** Lambda abstractions. */
public final class Lambda extends Expression {

  private Variable var;
  private Expression exp;

  public Lambda(Variable var, Expression exp) {
    this.var = var;
    this.exp = exp;
  }

  public Variable getVar() {
    return var;
  }

  public Expression getExp() {
    return exp;
  }

  @Override
  public String toString() {
    return "(\\" + var + " -> " + exp + ")";
  }

  @Override
  public Expression reduct(final Variable var, final Expression exp) {
    return this;
  }

  @Override
  public void accept(final Visitor visitor) {
    visitor.visit(this);
  }
}
