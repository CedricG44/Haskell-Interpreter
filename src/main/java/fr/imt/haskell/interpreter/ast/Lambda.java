package fr.imt.haskell.interpreter.ast;

public final class Lambda implements Expression {

    public final Variable name;
    public final Expression expression;

    public Lambda(Variable name, Expression expression){
        this.name = name;
        this.expression = expression;
    }

    @Override
    public Expression reduce() {
        if(isReducible()){
            return new Lambda(this.name, this.expression.reduce());
        }
        return this;
    }

    @Override
    public Expression substituate(Variable variable, Expression substitute) {
        return this.expression.substituate(variable, substitute);
    }

    @Override
    public boolean isReducible() {
        return this.expression.isReducible();
    }

    @Override
    public String toString() {
        return "(\\" + this.name + " -> " + this.expression + ")";
    }
}
