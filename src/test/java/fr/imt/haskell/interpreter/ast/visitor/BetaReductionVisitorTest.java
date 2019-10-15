package fr.imt.haskell.interpreter.ast.visitor;

import fr.imt.haskell.interpreter.ast.*;
import fr.imt.haskell.interpreter.ast.constants.Minus;
import fr.imt.haskell.interpreter.ast.constants.Number;
import fr.imt.haskell.interpreter.ast.constants.Plus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BetaReductionVisitorTest {

  private BetaReductionVisitor betaReductionVisitor;
  private Expression exp;
  private Expression expectedExp;

  @Before
  public void initVisitor() {
    betaReductionVisitor = new BetaReductionVisitor();
  }

  @Parameterized.Parameters(name = "{index}: {0}")
  public static Iterable<Object[]> data() {
    return Arrays.asList(
        new Object[][] {
          {new Plus(), new Plus()},
          {new Number(5), new Number(5)},
          {
            new Application(
                new Lambda(
                    new Variable("x"),
                    new Application(
                        new Application(new Plus(), new Variable("x")), new Variable("x"))),
                new Number(5)),
            new Application(new Application(new Plus(), new Number(5)), new Number(5))
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
          }
          /*,
          {
            new Application(
                new Lambda(
                    new Variable("z"),
                    new Application(
                        new Application(
                            new Plus(),
                            new Application(
                                new Lambda(
                                    new Variable("y"),
                                    new Application(new Minus(), new Variable("y"))),
                                new Number(5))),
                        new Variable("z"))),
                new Number(42)),
            new Application(
                new Application(new Plus(), new Application(new Minus(), new Number(5))),
                new Number(42))
          }*/
        });
  }

  public BetaReductionVisitorTest(Expression exp, Expression expectedExp) {
    this.exp = exp;
    this.expectedExp = expectedExp;
  }

  @Test
  public void betaReduction() {
    exp.accept(betaReductionVisitor);
    System.out.println("Reducted expression: " + betaReductionVisitor.getExp());
    assertEquals(expectedExp, betaReductionVisitor.getExp());
  }
}
