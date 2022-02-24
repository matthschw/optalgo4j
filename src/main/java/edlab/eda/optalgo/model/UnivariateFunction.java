package edlab.eda.optalgo.model;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;

public abstract class UnivariateFunction extends Function {
  
  public UnivariateFunction(final int noOfInputs) {
    super(noOfInputs);
  }

  public abstract BigDecimal evaluate(BigDecimal[] vals)
      throws NonMatchingDesignSpace;
}