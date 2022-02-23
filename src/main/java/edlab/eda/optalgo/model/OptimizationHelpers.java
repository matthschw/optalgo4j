package edlab.eda.optalgo.model;

public class OptimizationHelpers {

  public static String doubleArrayToString(final double[] vals) {

    final StringBuilder res = new StringBuilder("[");

    for (int i = 0; i < vals.length; i++) {
      if (i > 0) {
        res.append(" ");
      }

      res.append(vals[i]);
    }

    res.append("]");

    return res.toString();
  }

}
