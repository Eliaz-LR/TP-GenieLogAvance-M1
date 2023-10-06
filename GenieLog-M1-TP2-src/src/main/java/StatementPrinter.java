import java.text.NumberFormat;
import java.util.*;
import java.lang.StringBuffer;

public class StatementPrinter {

  public String print(Invoice invoice) {
    double totalAmount = 0;
    int volumeCredits = 0;
    String result = String.format("Statement for %s\n", invoice.customer);
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = perf.pieceTh;
      
      float thisAmount = perf.getPrix();

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience);
      totalAmount += thisAmount;
    }
    result += String.format("Amount owed is %s\n", frmt.format(totalAmount));
    result += String.format("You earned %s credits\n", volumeCredits);
    return result;
  }

}
