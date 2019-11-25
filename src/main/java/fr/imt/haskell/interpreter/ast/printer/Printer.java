package fr.imt.haskell.interpreter.ast.printer;

import fr.imt.haskell.interpreter.ast.Expression;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.AbstractMap;

public class Printer {

  private boolean printBelowList;

  private final Subject<String> root;
  public final Subject<AbstractMap.SimpleEntry<String, String>> changes;

  public Printer(boolean printBelowList, Expression root) {
    this.printBelowList = printBelowList;
    this.root = BehaviorSubject.createDefault(root.toString());
    changes = PublishSubject.create();

    changes
        .withLatestFrom(
            this.root,
            (changesValue, rootValue) -> {
              final String newRoot =
                  rootValue.replace(changesValue.getKey(), changesValue.getValue());
              this.root.onNext(newRoot);
              return newRoot;
            })
        .distinctUntilChanged()
        .subscribe(System.out::println);
  }

    public boolean isPrintBelowList() {
        return printBelowList;
    }
}
