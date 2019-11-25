package fr.imt.haskell.interpreter.ast.printer;

import fr.imt.haskell.interpreter.ast.Expression;
import java.util.AbstractMap;

public class Printer {

  private boolean printBelowList;
  private String root;

  public Printer(boolean printBelowList, Expression root) {
    this.printBelowList = printBelowList;
    this.root = root.toString();
  }

  public void onNext(AbstractMap.SimpleEntry<String, String> changes) {
    final String oldRoot = this.root;
    this.root = this.root.replace(changes.getKey(), changes.getValue());

    if (!(oldRoot.equals(this.root))) {
      System.out.println(this.root);
    }
  }

  public boolean isPrintBelowList() {
    return printBelowList;
  }
}
