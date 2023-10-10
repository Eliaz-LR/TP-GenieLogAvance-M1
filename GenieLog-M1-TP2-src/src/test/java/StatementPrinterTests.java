import static org.approvaltests.Approvals.verify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatementPrinterTests {

  @Test
  void exampleStatement() {
    HashMap<String, Play> plays = new HashMap<>();
    plays.put("hamlet", new Play("Hamlet", Play.PlayType.TRAGEDY));
    plays.put("as-like", new Play("As You Like It", Play.PlayType.COMEDY));
    plays.put("othello", new Play("Othello", Play.PlayType.TRAGEDY));

    Invoice invoice = new Invoice(
      "BigCo",
      List.of(
        new Performance("hamlet", 55),
        new Performance("as-like", 35),
        new Performance("othello", 40)
      )
    );

    StatementPrinter statementPrinter = new StatementPrinter();
    var result = statementPrinter.toText(invoice, plays);

    verify(result);
  }

  @Test
  void htmlStatement() {
    HashMap<String, Play> plays = new HashMap<>();
    plays.put("hamlet", new Play("Hamlet", Play.PlayType.TRAGEDY));
    plays.put("as-like", new Play("As You Like It", Play.PlayType.COMEDY));
    plays.put("othello", new Play("Othello", Play.PlayType.TRAGEDY));

    Invoice invoice = new Invoice(
      "BigCo",
      List.of(
        new Performance("hamlet", 55),
        new Performance("as-like", 35),
        new Performance("othello", 40)
      )
    );

    StatementPrinter statementPrinter = new StatementPrinter();
    System.out.println(statementPrinter.toHtml(invoice, plays));
  }
}
