package edlab.eda.optalgo.model;

import java.math.BigDecimal;

import edlab.eda.optalgo.parameters.ParameterRange;

public abstract class UnivariateOptimizer {

  public static final int MAX_NUM_OF_EVALS_DEFAULT = 500;
  public static final BigDecimal MAX_ERROR_DEFAULT = new BigDecimal("1e-3");
  
  protected UnivariateFunction func;
  protected int maxNumOfEvals;
  protected int numOfEvals;
  protected BigDecimal maxError;
  protected BigDecimal[] result = null;
  protected BigDecimal error;
  protected boolean verbose = false;
  protected ParameterRange[] range;

  public UnivariateOptimizer(final UnivariateFunction func,
      final int maxNumOfEvals) {
    this.func = func;
    this.maxNumOfEvals = maxNumOfEvals;
    this.numOfEvals = 0;
    this.maxError = MAX_ERROR_DEFAULT;
    this.range = new ParameterRange[func.getNoOfInputs()];
  }

  public UnivariateOptimizer(final UnivariateFunction func) {
    this.func = func;
    this.maxNumOfEvals = MAX_NUM_OF_EVALS_DEFAULT;
    this.numOfEvals = 0;
    this.maxError = MAX_ERROR_DEFAULT;
    this.range = new ParameterRange[func.getNoOfInputs()];
  }

  public UnivariateOptimizer(final UnivariateFunction func,
      final BigDecimal maxError) {
    this.func = func;
    this.numOfEvals = 0;
    this.maxError = maxError;
    this.maxNumOfEvals = MAX_NUM_OF_EVALS_DEFAULT;
    this.range = new ParameterRange[func.getNoOfInputs()];
  }

  public UnivariateOptimizer(final UnivariateFunction func,
      final int maxNumOfEvals, final BigDecimal maxError) {
    this.func = func;
    this.maxNumOfEvals = maxNumOfEvals;
    this.numOfEvals = 0;
    this.maxError = maxError;
    this.range = new ParameterRange[func.getNoOfInputs()];
  }

  UnivariateFunction getFunction() {
    return this.func;
  }

  public int getNumOfMaxEvals() {
    return this.maxNumOfEvals;
  }

  public UnivariateOptimizer setNumOfMaxEvals(final int maxNumOfEvals) {
    this.maxNumOfEvals = maxNumOfEvals;
    return this;
  }

  public int getNumOfEvals() {
    return this.numOfEvals;
  }

  public BigDecimal[] getResult() {
    return this.result;
  }

  public UnivariateOptimizer setMaxError(final BigDecimal maxError) {
    this.maxError = maxError;
    return this;
  }

  public BigDecimal getError() {
    return this.error;
  }

  public UnivariateOptimizer setVerbose(final boolean verbose) {
    this.verbose = verbose;
    return this;
  }

  public abstract UnivariateOptimizer run();

}
