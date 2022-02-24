package edlab.eda.optalgo.testfuns;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.model.UnivariateFunction;
import edlab.eda.optalgo.parameters.ContinuousParameterRange;

public class Rosenbrock extends UnivariateFunction {

  public Rosenbrock(final int n) {

    super(n, "Rosenbrock");

    for (int i = 0; i < n; i++) {
      this.range[i] = ContinuousParameterRange
          .getParameterRange(new BigDecimal("-5"), new BigDecimal("5"));
    }
  }

  @Override
  public BigDecimal evaluate(final BigDecimal[] vals)
      throws NonMatchingDesignSpace {

    if (vals.length == this.noOfInputs) {

      BigDecimal rtn = new BigDecimal("0");

      BigDecimal first;
      BigDecimal second;

      for (int i = 0; i < (this.noOfInputs - 1); i++) {

        first = vals[i + 1].subtract(vals[i].pow(2)).pow(2)
            .multiply(new BigDecimal("100"));
        second = new BigDecimal("1").subtract(vals[i]).pow(2);

        rtn = rtn.add(first).add(second);
      }

      return rtn;

    } else {
      throw new NonMatchingDesignSpace(vals.length, this.noOfInputs);
    }
  }
}