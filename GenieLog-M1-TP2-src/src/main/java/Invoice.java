import java.text.NumberFormat;
import java.io.File;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.Map;
import java.util.HashMap;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;


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
  
  protected String printToHTML() {
    
    Configuration configuration = new Configuration();
    ClassTemplateLoader loader = new ClassTemplateLoader(Invoice.class, "/templates");
    configuration.setClassForTemplateLoading(Invoice.class, "/templates");
    configuration.setDefaultEncoding("UTF-8");
    configuration.setLocale(Locale.FRANCE);
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    
   
    Map<String, Object> test = new HashMap<>();

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    double montantTotal = 0;
    double montantPiece = 0;
    int volumeCredits = 0;
    StringBuffer perfs = new StringBuffer();

    test.put("nameCompany", this.customer);

    for(Performance perf : this.performances){
      montantPiece = perf.getPrix();
      Play play = perf.pieceTh;

      perfs.append("<tr><td>" + perf.pieceTh.name + "</td><td>" + perf.audience + "</td><td>" + frmt.format(montantPiece) +"</td> </tr>");
      
      montantTotal += montantPiece;
      volumeCredits += Math.max(perf.audience - 30, 0);
      if (Type.comedy.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

    }
    test.put("perf", perfs);
    test.put("totalPrice", montantTotal);
    test.put("totalPoints", volumeCredits);
    
    Writer out = new StringWriter();


    try {
      final Template temp = configuration.getTemplate("test.ftlh");
      temp.process(test, out);
      System.out.println(out);
    }
    catch(Exception e){
      System.out.println("Template not found");

    }

    return out.toString();
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

 }