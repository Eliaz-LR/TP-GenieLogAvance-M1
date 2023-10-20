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

  @Test
  void testCustomerFidelityreduction() {
    Customer customer = new Customer("Good Customer");
    new Invoice(
      customer,
      List.of(
        new Performance(new Play("Hamlet", Play.PlayType.TRAGEDY), 155),
        new Performance(new Play("As You Like It", Play.PlayType.COMEDY), 35),
        new Performance(new Play("Othello", Play.PlayType.TRAGEDY), 60)
      )
    );

    // Good customer should now have +167 credits resulting in 15€ reduction for the next invoice
    System.out.println(
      "Number of credits after first order : " + customer.credit
    );

    Invoice invoice2 = new Invoice(
      customer,
      List.of(
        new Performance(new Play("Hamlet", Play.PlayType.TRAGEDY), 55),
        new Performance(new Play("As You Like It", Play.PlayType.COMEDY), 35),
        new Performance(new Play("Othello", Play.PlayType.TRAGEDY), 40)
      )
    );
    StatementPrinter statementPrinter = new StatementPrinter();
    var result2 = statementPrinter.toText(invoice2);
    verify(result2);
  }
}
