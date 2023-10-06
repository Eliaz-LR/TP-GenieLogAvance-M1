public class Play {

  public String name;

  public enum PlayType {
    TRAGEDY,
    COMEDY,
  }

  public PlayType type;

  public Play(String name, PlayType type) {
    this.name = name;
    this.type = type;
  }
}
