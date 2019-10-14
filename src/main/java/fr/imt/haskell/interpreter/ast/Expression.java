package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.visitor.Visitor;

/** Lambda calculus expression. */
public abstract class Expression {

    public abstract void accept(final Visitor visitor);
}
