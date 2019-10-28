package fr.imt.haskell.interpreter.ast;

/** Lambda abstractions. */
public final class Lambda extends Expression {

  private final Variable var;
  private final Expression exp;

  public Lambda(Variable var, Expression exp) {
    this.var = var;
    this.exp = exp;
  }

  @Override
  public Expression reduce() {
    return this;
  }

  @Override
  public Expression substitute(final Variable var, final Expression substitute) {
    System.out.println("[Lambda] Reduction step:  " + this);
    if (this.var.getValue().equals(var.getValue())) {
      return this;
    }
    return new Lambda(this.var, exp.substitute(var, substitute));
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
}
