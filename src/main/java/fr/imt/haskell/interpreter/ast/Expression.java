package fr.imt.haskell.interpreter.ast;

public interface Expression {

    Expression reduce();

    Expression substituate(Variable variable, Expression substitute);

    boolean isReducible();
}
