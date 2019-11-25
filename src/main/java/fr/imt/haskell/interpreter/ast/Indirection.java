package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.printer.Printer;

public class Indirection extends Expression {

  Expression exp;

  public Indirection(Expression exp) {
    this.exp = exp;
  }

  @Override
  public Expression reduceByNeed(Printer printer) {
    exp = exp.reduceByNeed(printer);
    return this.exp;
  }

  @Override
  public Expression instantiate(Variable var, Expression exp) {
//    return this.exp.instantiate(var, exp);
    return this;
  }

  @Override
  public String toString() {
    return exp.toString();
  }
}
