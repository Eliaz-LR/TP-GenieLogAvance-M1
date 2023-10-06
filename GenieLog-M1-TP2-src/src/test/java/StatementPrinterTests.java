import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", "tragedy"));
        plays.put("as-like",  new Play("As You Like It", "comedy"));
        plays.put("othello",  new Play("Othello", "tragedy"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance(plays.get("hamlet"), 55),
                new Performance(plays.get("as-like"), 35),
                new Performance(plays.get("othello"), 40)));

        StringBuffer statementPrinter = new StringBuffer();
        statementPrinter.append(invoice.print());

        verify(statementPrinter);
    }

    @Test
    void statementWithNewPlayTypes() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("henry-v",  new Play("Henry V", "history"));
        plays.put("as-like",  new Play("As You Like It", "pastoral"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance(plays.get("henry-v"), 53),
                new Performance(plays.get("as-like"), 55)));

        Assertions.assertThrows(Error.class, () -> {
            invoice.print();
        });
    }
}
