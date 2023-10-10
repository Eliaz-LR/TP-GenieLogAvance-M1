import freemarker.template.*;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {

  public String toText(Invoice invoice) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(String.format("Statement for %s\n", invoice.customer));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      buffer.append(
        String.format(
          "  %s: %s (%s seats)\n",
          perf.play.name,
          frmt.format(perf.amount),
          perf.audience
        )
      );
    }
    buffer.append(
      String.format("Amount owed is %s\n", frmt.format(invoice.totalAmount))
    );
    buffer.append(
      String.format("You earned %s credits\n", invoice.volumeCredits)
    );
    return buffer.toString();
  }

  public String toHtml(Invoice invoice) {
    try {
      Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
      cfg.setClassForTemplateLoading(StatementPrinter.class, "/views");
      cfg.setDefaultEncoding("UTF-8");
      cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
      cfg.setLocale(Locale.FRANCE);

      Template template = cfg.getTemplate("statement.ftlh");

      /* Create a data-model */
      Map<String, Object> root = new HashMap<>();
      root.put("invoice", invoice);

      StringWriter writer = new StringWriter();
      template.setOutputEncoding("UTF-8");
      template.process(root, writer);
      return writer.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
