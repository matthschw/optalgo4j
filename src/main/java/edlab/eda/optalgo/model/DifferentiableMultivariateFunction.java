package edlab.eda.optalgo.model;

import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;

public abstract class DifferentiableMultivariateFunction
    extends MultivariateFunction {

  public abstract double[][] jacobi(double[] vals)
      throws NonMatchingDesignSpace;

}