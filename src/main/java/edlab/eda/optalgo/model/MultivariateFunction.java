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

  public abstract BigDecimal[] evaluate(BigDecimal[] vals)
      throws NonMatchingDesignSpace;

}