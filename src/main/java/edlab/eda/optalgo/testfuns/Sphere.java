package edlab.eda.optalgo.testfuns;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.model.UnivariateFunction;
import edlab.eda.optalgo.parameters.ContinuousParameterRange;

public class Sphere extends UnivariateFunction {

  public Sphere(final int noOfInputs) {
    super(noOfInputs, "sphere");

    for (int i = 0; i < noOfInputs; i++) {
      this.range[i] = ContinuousParameterRange
          .getParameterRange(new BigDecimal("-5"), new BigDecimal("5"));
    }
  }

  @Override
  public BigDecimal evaluate(BigDecimal[] vals) throws NonMatchingDesignSpace {

    if (vals.length == this.noOfInputs) {

      BigDecimal rtn = BigDecimal.ZERO;

      for (int i = 0; i < this.noOfInputs; i++) {
        rtn = rtn.add(vals[i].pow(2));
      }

      return rtn;

    } else {
      throw new NonMatchingDesignSpace(vals.length, this.noOfInputs);
    }
  }
}