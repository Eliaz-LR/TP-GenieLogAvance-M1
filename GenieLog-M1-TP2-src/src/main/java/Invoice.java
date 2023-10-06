import java.text.NumberFormat;
import java.lang.StringBuffer;
import java.util.*;

public class Invoice {

  public String customer;
  public List<Performance> performances;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

  protected StringBuffer print() {

    double totalAmount = 0;
    int volumeCredits = 0;
    StringBuffer result = new StringBuffer("Statement for " + this.customer +"\n");
    
    // add

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : this.performances) {
      Play play = perf.pieceTh;
      
      float thisAmount = perf.getPrix();

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      result.append("  " + play.name + ": " + frmt.format(thisAmount) +" (" + perf.audience + " seats)\n");
      totalAmount += thisAmount;
    }
    result.append("Amount owed is "+ frmt.format(totalAmount)+ "\n");
    result.append("You earned "+ volumeCredits + " credits\n");
    return result;




  }


  
}
