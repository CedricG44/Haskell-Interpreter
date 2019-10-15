package fr.imt.haskell.interpreter.ast;

public class Constant implements Expression {

    @Override
    public Expression reduce() {
        return this;
    }

    @Override
    public Expression substituate(Variable variable, Expression substitute) {
        return this;
    }

    @Override
    public boolean isReducible() {
        return false;
    }

}
