package fr.imt.haskell.interpreter.ast.visitor;

import fr.imt.haskell.interpreter.ast.Constant;
import fr.imt.haskell.interpreter.ast.Expression;
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

  @Before
  public void initVisitor() {
    betaReductionVisitor = new BetaReductionVisitor();
  }

  @Parameterized.Parameters(name = "{index}: {0}")
  public static Iterable<Object[]> data() {
    return Arrays.asList(new Object[][] {{new Plus()}});
  }

  public BetaReductionVisitorTest(Expression exp) {
    this.exp = exp;
  }

  @Test
  public void betaReduction() {
    exp.accept(betaReductionVisitor);
    System.out.println("Reducted expression: " + betaReductionVisitor.getExp());
  }
}
