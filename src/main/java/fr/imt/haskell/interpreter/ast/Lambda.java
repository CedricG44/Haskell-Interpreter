package fr.imt.haskell.interpreter.ast;

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
    return "(λ" + var + ". " + exp + ")";
  }
}
