package fr.imt.haskell.interpreter.ast;

public class Variable implements Expression {

    public final String value;

    public Variable(String value){
        this.value = value;
    }

    @Override
    public Expression reduce() {
        return this;
    }

    @Override
    public Expression substituate(Variable variable, Expression substitute) {
        return variable.equals(this) ? substitute : this;
    }

    @Override
    public boolean isReducible() {
        return false;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return value.equals(variable.value);
    }
}
