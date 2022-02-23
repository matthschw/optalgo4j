package edlab.eda.optalgo.testfuns;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.model.DifferntiableUnivariateFunction;
import edlab.eda.optalgo.parameters.ContinuousParameterRange;

public class Himmelblau extends DifferntiableUnivariateFunction {

  public Himmelblau() {

    super(2);

    for (int i = 0; i < 2; i++) {
      this.range[i] = ContinuousParameterRange
          .getParameterRange(new BigDecimal("-5"), new BigDecimal("5"));
    }
  }

  @Override
  public BigDecimal evaluate(final BigDecimal[] vals)
      throws NonMatchingDesignSpace {

    if (vals.length == this.noOfInputs) {

      return vals[0].pow(2).add(vals[1]).subtract(new BigDecimal("11")).pow(2)
          .add(
              vals[0].add(vals[1].pow(2)).subtract(new BigDecimal("7")).pow(2));
    } else {
      throw new NonMatchingDesignSpace(vals.length, this.noOfInputs);
    }

  }

  @Override
  public BigDecimal[] gradient(final BigDecimal[] vals)
      throws NonMatchingDesignSpace {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public BigDecimal[][] hessian(final BigDecimal[] vals)
      throws NonMatchingDesignSpace {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * @Override public double[] gradient(double[] vals) throws
   * NonMatchingArrayLength {
   * 
   * if (vals.length == this.noOfInputs) {
   * 
   * double rtn[] = new double[this.noOfInputs];
   * 
   * rtn[0] = 2 * (2 * Math.pow(vals[0], 3) + 3 * Math.pow(vals[0], 2) - 20 *
   * vals[0] + Math.pow(vals[1], 2) - 18); rtn[1] = 4 * vals[1] * (vals[0] +
   * Math.pow(vals[1], 2) - 7);
   * 
   * return rtn;
   * 
   * } else { throw new NonMatchingArrayLength(vals.length, this.noOfInputs); }
   * 
   * }
   * 
   * @Override public double[][] hessian(double[] vals) throws
   * NonMatchingArrayLength {
   * 
   * if (vals.length == this.noOfInputs) {
   * 
   * double rtn[][] = new double[this.noOfInputs][this.noOfInputs];
   * 
   * rtn[0][0] = 4 * (3 * Math.pow(vals[0], 2) + 3 * vals[0] - 10); rtn[0][1] =
   * 4 * vals[1]; rtn[1][0] = 4 * vals[1]; rtn[1][1] = 4 * (vals[0] + 3 *
   * Math.pow(vals[1], 2) - 7);
   * 
   * return rtn;
   * 
   * } else { throw new NonMatchingArrayLength(vals.length, this.noOfInputs); }
   * }
   */

}
