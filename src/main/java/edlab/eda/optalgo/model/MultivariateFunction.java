package edlab.eda.optalgo.model;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;

public abstract class MultivariateFunction {

  protected int noOfInputs;
  protected int noOfOutputs;
  protected BigDecimal[] lowerBound;
  protected BigDecimal[] upperBound;

  public abstract BigDecimal[] evaluate(BigDecimal[] vals)
      throws NonMatchingDesignSpace;

}