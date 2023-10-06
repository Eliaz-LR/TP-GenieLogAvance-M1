public class Performance {

  public Play pieceTh;
  public int audience;

  public Performance(Play pieceTh, int audience) {
    this.pieceTh = pieceTh;
    this.audience = audience;
  }

  protected float getPrix(){

    float thisAmount = 0;

    switch (this.pieceTh.type) {
      case "tragedy":
        thisAmount = 400;
        if (this.audience > 30) {
          thisAmount += 10 * (this.audience - 30);
        }
        break;
      case "comedy":
        thisAmount = 300 + 3 * this.audience;
        if (this.audience > 20) {
          thisAmount += 100 + 5 * (this.audience - 20);
        }
        
        break;
      default:
        throw new Error("unknown type: ${play.type}");
      }

      return thisAmount;


  }

   */

}
