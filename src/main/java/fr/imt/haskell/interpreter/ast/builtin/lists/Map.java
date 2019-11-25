package fr.imt.haskell.interpreter.ast.builtin.lists;

import fr.imt.haskell.interpreter.ast.Application;
import fr.imt.haskell.interpreter.ast.Expression;
import fr.imt.haskell.interpreter.ast.Lambda;
import fr.imt.haskell.interpreter.ast.Variable;
import fr.imt.haskell.interpreter.ast.builtin.ConditionalExpression;
import fr.imt.haskell.interpreter.ast.builtin.Recursion;
import fr.imt.haskell.interpreter.ast.builtin.UnaryExpression;
import fr.imt.haskell.interpreter.ast.constants.List;
import fr.imt.haskell.interpreter.ast.printer.Printer;

import java.util.AbstractMap;

import static fr.imt.haskell.interpreter.ast.constants.List.Cons;
import static fr.imt.haskell.interpreter.ast.constants.List.Nil;

/** List map built-in functions. */
public final class Map extends UnaryExpression {

  private Lambda lambda;

  public Map(Expression expression, Lambda lambda) {
    super(expression);
    this.lambda = lambda;
  }

  @Override
  public Expression reduce(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = map(this.lambda, (List) exp).reduce(printer);
    printer.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByValue(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = map(this.lambda, (List) exp.reduceByValue(printer));
    printer.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression reduceByNeed(final Printer printer) {
    final String oldExp = toString();
    final Expression newExp = map(this.lambda, (List) exp).reduceByNeed(printer);
    printer.onNext(new AbstractMap.SimpleEntry<>(oldExp, newExp.toString()));
    return newExp;
  }

  @Override
  public Expression instantiate(final Variable var, final Expression exp) {
    return new Map(this.exp.instantiate(var, exp), lambda);
  }

  private Expression map(final Lambda lambda, final List list) {
    return new Application(
        new Application(
            new Recursion(
                new Lambda(
                    new Variable("map"),
                    new Lambda(
                        new Variable("lambda"),
                        new Lambda(
                            new Variable("list"),
                            new ConditionalExpression(
                                new Null(new Variable("list")),
                                Nil(),
                                Cons(
                                    new Application(
                                        new Variable("lambda"), new Head(new Variable("list"))),
                                    Cons(
                                        new Application(
                                            new Application(
                                                new Variable("map"), new Variable("lambda")),
                                            new Tail(new Variable("list"))),
                                        Nil()))))))),
            lambda),
        list);
  }

  @Override
  public String toString() {
    return "(map " + lambda + " " + exp + ")";
  }
}
