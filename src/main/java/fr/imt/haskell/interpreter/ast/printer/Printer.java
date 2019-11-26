package fr.imt.haskell.interpreter.ast.printer;

import fr.imt.haskell.interpreter.ast.Expression;
import org.apache.commons.lang3.StringUtils;

import java.util.AbstractMap;

public class Printer {

  private boolean printBelowList;
  private int reductionSteps = 0;
  private String root;

  public Printer(boolean printBelowList, Expression root) {
    this.printBelowList = printBelowList;
    this.root = root.toString();
  }

  public void onNext(final AbstractMap.SimpleEntry<String, String> changes) {
    final String oldRoot = root;
    root = StringUtils.replaceOnce(root, changes.getKey(), changes.getValue());

    if (!(oldRoot.equals(root))) {
      System.out.println(root);
    }
  }

  public boolean isPrintBelowList() {
    return printBelowList;
  }

  public void incrementSteps() {
    reductionSteps++;
  }

  public int getReductionSteps() {
    return reductionSteps;
  }
}
