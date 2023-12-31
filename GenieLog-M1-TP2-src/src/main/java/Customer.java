import java.util.UUID;

public class Customer {

  public String name;
  public UUID idNumber;
  public int credit;

  Customer(String name) {
    this.name = name;
    this.idNumber = UUID.randomUUID();
    this.credit = 0;
  }

  public String toString() {
    return name;
  }

  public int getCredit() {
    return credit;
  }
}
