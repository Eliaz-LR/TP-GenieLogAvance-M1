import java.util.*;

public class Invoice {

  public String customer;
  public List<Performance> performances;
  public float totalAmount = 0;
  public int volumeCredits = 0;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;

    for (Performance perf : performances) {
      Play play = perf.play;
      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (play.type == Play.PlayType.COMEDY) volumeCredits +=
        Math.floor(perf.audience / 5);

      // print line for this order
      totalAmount += perf.amount;
    }
  }

  public String getCustomer() {
    return customer;
  }

  public List<Performance> getPerformances() {
    return performances;
  }
}
