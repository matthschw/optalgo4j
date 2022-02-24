package edlab.eda.optalgo.model;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;

public abstract class DifferntiableMultivariateFunction
    extends MultivariateFunction {

  public DifferntiableMultivariateFunction(final int noOfInputs,
      final int noOfOutputs) {
    super(noOfInputs, noOfOutputs);
  }

  public DifferntiableMultivariateFunction(final int noOfInputs,
      final int noOfOutputs, String name) {
    super(noOfInputs, noOfOutputs, name);
  }

  public abstract BigDecimal[] gradient(BigDecimal[] vals)
      throws NonMatchingDesignSpace;

  public abstract BigDecimal[][] hessian(BigDecimal[] vals)
      throws NonMatchingDesignSpace;
}