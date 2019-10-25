package fr.imt.haskell.interpreter.ast;

import fr.imt.haskell.interpreter.ast.builtin.Minus;
import fr.imt.haskell.interpreter.ast.builtin.Number;
import fr.imt.haskell.interpreter.ast.builtin.Plus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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
            new Application(new Lambda(new Variable("x"), new Variable("y")), new Number(5)),
            new Variable("y")
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
            new Plus(new Minus(new Number(5)), new Number(42))
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
    final Expression reducedExp = exp.reduce();
    System.out.println("Reduced expression: " + reducedExp + "\n");
    assertEquals(expectedExp, reducedExp);
  }
}
