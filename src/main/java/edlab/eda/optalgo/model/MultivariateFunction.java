package edlab.eda.optalgo.model;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.parameters.ParameterRange;

public abstract class MultivariateFunction extends Function {

  protected int noOfInputs;
  protected int noOfOutputs;
  protected ParameterRange[] range;

  public MultivariateFunction(int noOfInputs, int noOfOutputs) {
    super(noOfInputs);
    this.noOfOutputs = noOfOutputs;
  }

  public MultivariateFunction(int noOfInputs, int noOfOutputs, String name) {
    super(noOfInputs, name);
    this.noOfOutputs = noOfOutputs;
  }

  /**
   * Get the number of output values of function
   * 
   * @return number of output values
   */
  public int getNoOfOutputs() {
    return this.noOfOutputs;
  }

  public abstract BigDecimal[] evaluate(BigDecimal[] values)
      throws NonMatchingDesignSpace;
}