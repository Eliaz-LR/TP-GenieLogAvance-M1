import static org.approvaltests.Approvals.verify;

import java.util.List;
import org.junit.jupiter.api.Test;

public class StatementPrinterTests {

  @Test
  void exampleStatement() {
    Customer customer = new Customer("BigCo");
    Invoice invoice = new Invoice(
      customer,
      List.of(
        new Performance(new Play("Hamlet", Play.PlayType.TRAGEDY), 55),
        new Performance(new Play("As You Like It", Play.PlayType.COMEDY), 35),
        new Performance(new Play("Othello", Play.PlayType.TRAGEDY), 40)
      )
    );

    StatementPrinter statementPrinter = new StatementPrinter();
    var result = statementPrinter.toText(invoice);

    verify(result);
  }

  @Test
  void htmlStatement() {
    Customer customer = new Customer("BigCo");
    Invoice invoice = new Invoice(
      customer,
      List.of(
        new Performance(new Play("Hamlet", Play.PlayType.TRAGEDY), 55),
        new Performance(new Play("As You Like It", Play.PlayType.COMEDY), 35),
        new Performance(new Play("Othello", Play.PlayType.TRAGEDY), 40)
      )
    );

    StatementPrinter statementPrinter = new StatementPrinter();
    var result = statementPrinter.toHtml(invoice);
    verify(result);
  }
}
