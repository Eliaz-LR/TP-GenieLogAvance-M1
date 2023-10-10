import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import java.util.List;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    

    @Test
    void exampleStatement() {

    
        
        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", Type.tragedy));
        plays.put("as-like",  new Play("As You Like It", Type.comedy));
        plays.put("othello",  new Play("Othello", Type.tragedy));
        
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance(plays.get("hamlet"), 55),
                new Performance(plays.get("as-like"), 35),
                new Performance(plays.get("othello"), 40)));

        StringBuffer statementPrinter = new StringBuffer();
        statementPrinter.append(invoice.printText());

        verify(statementPrinter);
    }

  
}
