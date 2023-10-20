import java.util.*;

public class Invoice {

  public Customer customer;
  public List<Performance> performances;
  public float totalAmount = 0;
  public int volumeCredits = 0;

  public Invoice(Customer customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;

    // calculate "total amount" and "volume credits"
    for (Performance perf : performances) {
      Play play = perf.play;
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (play.type == Play.PlayType.COMEDY) {
        volumeCredits += Math.floor(perf.audience / 5);
      }

      totalAmount += perf.amount;
    }
  }

  public Customer getCustomer() {
    return customer;
  }

  public List<Performance> getPerformances() {
    return performances;
  }

  public float getTotalAmount() {
    return totalAmount;
  }

  public int getVolumeCredits() {
    return volumeCredits;
  }
}
