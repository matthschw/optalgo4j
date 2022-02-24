package edlab.eda.optalgo.model;

import java.math.BigDecimal;
import java.math.MathContext;

public class OptimizationHelpers {

  public static String toString(final BigDecimal[] vals) {

    final StringBuilder res = new StringBuilder("[");

    for (int i = 0; i < vals.length; i++) {
      
      if (i > 0) {
        res.append(" ");
      }

      res.append(vals[i].round(MathContext.DECIMAL32).toString());
    }

    res.append("]");

    return res.toString();
  }
}