public class Performance {

  public Play play;
  public int audience;
  public float amount;

  public Performance(Play play, int audience) {
    this.play = play;
    this.audience = audience;

    switch (play.type) {
      case TRAGEDY:
        amount = 400;
        if (audience > 30) {
          amount += 10 * (audience - 30);
        }
        break;
      case COMEDY:
        amount = 300;
        if (audience > 20) {
          amount += 100 + 5 * (audience - 20);
        }
        amount += 3 * audience;
        break;
      default:
        throw new Error("unknown type: ${play.type}");
    }
  }

  public Play getPlay() {
    return play;
  }

  public int getAudience() {
    return audience;
  }
}
