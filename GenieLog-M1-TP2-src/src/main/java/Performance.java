public class Performance {

  public Play pieceTh;
  public int audience;

  public Performance(Play pieceTh, int audience) {
    this.pieceTh = pieceTh;
    this.audience = audience;
  }

  protected Type getType(){
    return this.pieceTh.type;

  }
  /* Fonction getPrix, retourne un double (le prix de la performance)
   * Si la pièce est une comédie: On paye initialement 400$ et on 
   *                              rajoute 10$ par spectateur dès lors
   *                              qu'ils sont plus de 30.
   * Si la pièce est une tragédie: On paye initialement 300$ et 
   *                              et 3*le nombre de spectateur. Puis
   *                              on ajoute 100 si le nombre de spect.
   *                              est supérieur à 20, de manière analogue
   *                              on ajoute 5€ pour chaque spect au dessus
   *                              de 20€
   * 
   * On retourne le montant à payer en sortie.
   */

  protected double getPrix(){

    double thisAmount = 0;

    switch (this.getType()) {
      
      case tragedy:
        thisAmount = 400;
        if (this.audience > 30) {
          thisAmount += 10 * (this.audience - 30);
        }
        break;

      case comedy:
        thisAmount = 300 + 3 * this.audience;
        if (this.audience > 20) {
          thisAmount += 100 + 5 * (this.audience - 20);
        }
        break;    
    }
      return thisAmount;
  }
}
