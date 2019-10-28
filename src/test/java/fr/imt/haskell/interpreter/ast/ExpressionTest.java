package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.builtin.ConditionalExpression;
import fr.imt.haskell.interpreter.ast.builtin.arithmetics.*;
import fr.imt.haskell.interpreter.ast.builtin.logicals.And;
import fr.imt.haskell.interpreter.ast.builtin.logicals.Not;
import fr.imt.haskell.interpreter.ast.constants.Boolean;
import fr.imt.haskell.interpreter.ast.constants.Number;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ExpressionTest {

  private final Expression exp;
  private final Expression expectedExp;

  @Parameterized.Parameters(name = "{index}: {0}")
  public static Iterable<Object[]> data() {
    return Arrays.asList(
        new Object[][] {
          {new Number(5), new Number(5)},
          {
            new Application(
                new Lambda(new Variable("x"), new Plus(new Variable("x"), new Variable("x"))),
                new Number(5)),
            new Number(10)
          },
          {
            new Application(
                new Application(
                    new Lambda(new Variable("x"), new Variable("x")),
                    new Lambda(new Variable("y"), new Variable("y"))),
                new Lambda(new Variable("z"), new Variable("z"))),
            new Lambda(new Variable("z"), new Variable("z"))
          },
          {
            new Application(
                new Lambda(new Variable("x"), new Variable("x")),
                new Application(
                    new Lambda(new Variable("y"), new Variable("y")),
                    new Lambda(new Variable("z"), new Variable("z")))),
            new Lambda(new Variable("z"), new Variable("z"))
          },
          {
            new Application(
                new Lambda(
                    new Variable("z"),
                    new Plus(
                        new Application(
                            new Lambda(new Variable("y"), new Minus(new Variable("y"))),
                            new Number(5)),
                        new Variable("z"))),
                new Number(42)),
            new Number(37)
          },
          {
            new Application(
                new Lambda(new Variable("x"), new Times(new Variable("x"), new Variable("x"))),
                new Number(7)),
            new Number(49)
          },
          {
            new Application(
                new Lambda(new Variable("x"), new Divide(new Variable("x"), new Variable("x"))),
                new Number(78000)),
            new Number(1)
          },
          {
            new Application(
                new Lambda(new Variable("x"), new And(new Boolean(true), new Variable("x"))),
                new Boolean(true)),
            new Boolean(true)
          },
          {new Plus(new Number(20), new Number(10)), new Number(30)},
          {new Not(new Boolean(true)), new Boolean(false)},
          {new Minus(new Number(20)), new Number(-20)},
          {new Equal(new Number(20), new Number(20)), new Boolean(true)},
          {new Equal(new Plus(new Number(5), new Number(15)), new Number(20)), new Boolean(true)},
          {
            new Equal(
                new Plus(new Number(5), new Plus(new Number(5), new Number(10))), new Number(20)),
            new Boolean(true)
          },
          {new Equal(new Minus(new Number(5)), new Number(-5)), new Boolean(true)},
          {new Equal(new Minus(new Minus(new Number(5))), new Number(5)), new Boolean(true)},
          {
            new ConditionalExpression(
                new Not(new Equal(new Number(20), new Number(20))),
                new Number(42),
                new Number(666)),
            new Number(666)
          },
          {
            new ConditionalExpression(
                new Not(new Equal(new Number(20), new Number(20))),
                new Number(42),
                new Application(
                    new Lambda(new Variable("x"), new Plus(new Variable("x"), new Variable("x"))),
                    new Number(5))),
            new Number(10)
          }
        });
  }

  public ExpressionTest(Expression exp, Expression expectedExp) {
    this.exp = exp;
    this.expectedExp = expectedExp;
  }

  @Test
  public void betaReduction() {
    System.out.println("\nExpression to reduce: " + exp);
    try {
      final Expression reducedExp = exp.reduce();
      System.out.println("Reduced expression: " + reducedExp + "\n");
      assertEquals(expectedExp, reducedExp);
    } catch (Exception ex) {
      assertTrue(ex instanceof UnsupportedOperationException);
      assertEquals("Weak Head Normal Form !", ex.getMessage());
      System.out.println(ex.getMessage());
    }
  }
}
