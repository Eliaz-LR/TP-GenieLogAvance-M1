import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {

  public String print(Invoice invoice, HashMap<String, Play> plays) {
    // amount of money for this order
    float totalAmount = 0;
    int volumeCredits = 0;

    StringBuffer buffer = new StringBuffer();
    buffer.append(String.format("Statement for %s\n", invoice.customer));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      // amount of money for this performance
      float thisAmount = 0;

      switch (play.type) {
        case TRAGEDY:
          thisAmount = 400;
          if (perf.audience > 30) {
            thisAmount += 10 * (perf.audience - 30);
          }
          break;
        case COMEDY:
          thisAmount = 300;
          if (perf.audience > 20) {
            thisAmount += 100 + 5 * (perf.audience - 20);
          }
          thisAmount += 3 * perf.audience;
          break;
        default:
          throw new Error("unknown type: ${play.type}");
      }

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (play.type == Play.PlayType.COMEDY) volumeCredits +=
        Math.floor(perf.audience / 5);

      // print line for this order
      buffer.append(
        String.format(
          "  %s: %s (%s seats)\n",
          play.name,
          frmt.format(thisAmount),
          perf.audience
        )
      );
      totalAmount += thisAmount;
    }
    buffer.append(
      String.format("Amount owed is %s\n", frmt.format(totalAmount))
    );
    buffer.append(String.format("You earned %s credits\n", volumeCredits));
    return buffer.toString();
  }
}
