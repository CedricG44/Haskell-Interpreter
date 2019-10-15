package fr.imt.haskell.interpreter.ast;

public final class Application implements Expression {

    public final Expression expression;
    public final Expression argument;

    public Application(Expression expression, Expression argument){
        this.expression = expression;
        this.argument = argument;
    }

    @Override
    public boolean isReducible() {
        return this.expression.isReducible() || this.argument.isReducible() || this.expression instanceof Lambda;
    }

    @Override
    public Expression reduce() {

        Expression expression = null;

        if (this.expression.isReducible())
        {
            expression = new Application(this.expression.reduce(), this.argument);
        }
        else if (this.argument.isReducible())
        {
            expression = new Application(this.expression, this.argument.reduce());
        }
        else if (this.expression instanceof Lambda)
        {
            Lambda lambda = (Lambda) this.expression;
            expression = this.expression.substituate(lambda.name, this.argument);
        }

        if (expression.isReducible()) {
            return expression.reduce();
        }

        return expression;
    }

    @Override
    public Expression substituate(Variable variable, Expression substitute) {
        return new Application(this.expression.substituate(variable, substitute), this.argument.substituate(variable, substitute));
    }

    @Override
    public String toString() {
        return "(" + this.expression + " " + this.argument + ")";
    }
}
