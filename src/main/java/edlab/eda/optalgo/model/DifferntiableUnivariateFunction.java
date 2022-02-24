package edlab.eda.optalgo.model;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;

public abstract class DifferntiableUnivariateFunction
    extends UnivariateFunction {

  public DifferntiableUnivariateFunction(final int noOfInputs) {
    super(noOfInputs);
  }

  public DifferntiableUnivariateFunction(final int noOfInputs, String name) {
    super(noOfInputs, name);
  }

  public abstract BigDecimal[] gradient(BigDecimal[] vals)
      throws NonMatchingDesignSpace;

  public abstract BigDecimal[][] hessian(BigDecimal[] vals)
      throws NonMatchingDesignSpace;
}