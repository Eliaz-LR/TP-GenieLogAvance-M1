import java.text.NumberFormat;
import java.lang.StringBuffer;
import java.lang.*;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.util.*;

public class Invoice {

  public String customer;
  public List<Performance> performances;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }


  /* Fonction de création d'une facture (Version StringBuffer)
   * (StringBuffer) resultat : Facture sous forme de chaine de caractère. (retour)
   * (double) montantTotal   : montant total à régler.
   * (double) montantPiece   : montant d'une pièce à régler.
   * (int)    volumeCrédits  : Points de fidélité gagné.
   *          -> On gagne un point de fidélité par spectateur au dessus de 30.
   *          -> De plus, si c'est une comédie, on ajoute le nombre de spectateur
   *             divisé par 5 (arrondi à l'entier inférieur) en points de fidélité.
   * (NumberFormat) frmt     : devise.
   *
   * getPrix()               : Donne le prix d'une performance
  */
  protected StringBuffer printText() {

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    double montantTotal = 0;
    int volumeCredits = 0;
    StringBuffer resultat = new StringBuffer("Statement for " + this.customer +"\n");
    for (Performance perf : this.performances) {

      System.out.println(perf.pieceTh.name);
      Play play = perf.pieceTh;
      double montantPiece = perf.getPrix();

      Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
      configuration.setClassForTemplateLoading(Invoice.class, "/templates");
      configuration.setDefaultEncoding("UTF-8");
      configuration.setLocale(Locale.FRANCE);
      configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);



      // Ajout de crédits
      volumeCredits += Math.max(perf.audience - 30, 0);
      if (Type.comedy.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // ajoute les lignes dans cet ordre
      resultat.append("  " + play.name + ": " + frmt.format(montantPiece));
      resultat.append(" (" + perf.audience + " seats)\n");
      montantTotal += montantPiece;
    }

    resultat.append("Amount owed is "+ frmt.format(montantTotal)+ "\n");
    resultat.append("You earned "+ volumeCredits + " credits\n");

    return resultat;
  }




}

/* FONCTION printToHTML
 * On utilise cette série de commandes pour inverser la liste de performances
 *     List<Performance> perfInv = new ArrayList<Performance>();
 *     perfInv.addAll(performances);
 *     Collections.reverse(perfInv);
 *
 *  -> On pourra intégrer plus facilement le code à l'aide de ça !
 *
 */