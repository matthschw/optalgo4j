package edlab.eda.optalgo.testfuns;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.model.UnivariateFunction;
import edlab.eda.optalgo.parameters.ContinuousParameterRange;

public class Rastrigin extends UnivariateFunction {

  private final BigDecimal a;

  public Rastrigin(final int n, final BigDecimal a) {

    super(n);
    this.a = a;

    for (int i = 0; i < n; i++) {
      this.range[i] = ContinuousParameterRange
          .getParameterRange(new BigDecimal(-5.12), new BigDecimal(5.12));
    }
  }

  @Override
  public BigDecimal evaluate(final BigDecimal[] vals) throws NonMatchingDesignSpace {

    if (vals.length == this.noOfInputs) {

      BigDecimal rtn = this.a.multiply(new BigDecimal(this.noOfInputs));

      for (int i = 0; i < this.noOfInputs; i++) {
        rtn = rtn.add(vals[i].pow(2).subtract(new BigDecimal(2).multiply(
            new BigDecimal(Math.cos(2 * Math.PI * vals[i].doubleValue())))));
      }

      return rtn;
      
    } else {
      throw new NonMatchingDesignSpace(vals.length, this.noOfInputs);
    }

  }
  /*
   * @Override public double[] gradient(double[] vals) throws
   * NonMatchingArrayLength {
   * 
   * if (vals.length == this.noOfInputs) { double rtn[] = new
   * double[this.noOfInputs];
   * 
   * for (int i = 0; i < this.noOfInputs; i++) { rtn[i] = 2 * vals[i] + 4 *
   * Math.PI * Math.sin(2 * Math.PI * vals[i]); }
   * 
   * return rtn;
   * 
   * } else { throw new NonMatchingArrayLength(vals.length, this.noOfInputs); }
   * 
   * }
   * 
   * @Override public double[][] hessian(double[] vals) { double rtn[][] = new
   * double[this.noOfInputs][this.noOfInputs]; return rtn; }
   */
}