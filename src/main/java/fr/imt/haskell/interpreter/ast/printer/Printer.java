package fr.imt.haskell.interpreter.ast.printer;

import fr.imt.haskell.interpreter.ast.Expression;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import javafx.util.Pair;

public class Printer {

  private final Subject<String> root;
  public final Subject<Pair<String, String>> changes;

  public Printer(Expression root) {
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
}
