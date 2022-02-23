package edlab.eda.optalgo.algorithms;

import java.math.BigDecimal;
import java.math.MathContext;

import edlab.eda.optalgo.exceptions.NoLowerAndUpperBoundedRange;
import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.model.UnivariateFunction;
import edlab.eda.optalgo.model.UnivariateOptimizer;

public class NelderMead extends UnivariateOptimizer {

  private BigDecimal alpha = new BigDecimal("1");
  private BigDecimal beta = new BigDecimal("0.5");
  private BigDecimal gamma = new BigDecimal("2");
  private BigDecimal sigma = new BigDecimal("0.5");
  private BigDecimal[][] initialSimplex = null;

  public NelderMead(final UnivariateFunction func, final int maxNumOfEvals) {
    super(func, maxNumOfEvals);
  }

  public NelderMead(final UnivariateFunction func) {
    super(func);
  }

  public NelderMead(final UnivariateFunction func, final BigDecimal maxError) {
    super(func, maxError);
  }

  public NelderMead(final UnivariateFunction func, final int maxNumOfEvals,
      final BigDecimal maxError) {
    super(func, maxNumOfEvals, maxError);
  }

  @Override
  public UnivariateOptimizer run() {

    if (this.checkControlParameters()) {

      this.numOfEvals = 0;

      final BigDecimal[][] simplex = new BigDecimal[this.func.getNoOfInputs()
          + 1][this.func.getNoOfInputs()];

      final BigDecimal[] fun = new BigDecimal[this.func.getNoOfInputs() + 1];

      for (int i = 0; i < simplex.length; i++) {

        try {
          simplex[i] = this.func.getRandomPosition();
        } catch (final NoLowerAndUpperBoundedRange e) {
          e.printStackTrace();
          return null;
        }
      }

      for (int i = 0; i < simplex.length; i++) {
        try {
          fun[i] = this.func.evaluate(simplex[i]);
          this.numOfEvals++;
        } catch (final NonMatchingDesignSpace e) {
          return null;
        }
      }

      sort(simplex, fun);

      BigDecimal[] mean;
      BigDecimal[] reflection;
      BigDecimal[] expansion;
      BigDecimal[] contraction;

      BigDecimal fun_reflection;
      BigDecimal fun_expansion;
      BigDecimal fun_contraction;

      boolean allTheSame;
      BigDecimal ratio;

      while ((fun[0].compareTo(this.maxError) > 0)
          && (this.numOfEvals < this.maxNumOfEvals)) {

        allTheSame = true;

        for (int i = 1; i < simplex.length; i++) {
          ratio = fun[i].subtract(fun[0]).divide(fun[0], MathContext.DECIMAL64)
              .abs();
          if (ratio.compareTo(new BigDecimal("0.01")) > 0) {
            allTheSame = false;
            break;
          }
        }

        if (allTheSame) {

          for (int i = 1; i < simplex.length; i++) {
            if (Math.random() > 0.98) {
              try {
                simplex[i] = this.func.getRandomPosition();
                fun[i] = this.func.evaluate(simplex[i]);
                this.numOfEvals++;
              } catch (final NoLowerAndUpperBoundedRange e) {
                return null;
              } catch (final NonMatchingDesignSpace e) {
                return null;
              }
            }
          }
          sort(simplex, fun);
        }

        if (this.verbose) {
          System.out.println(
              "Evaluation:" + this.numOfEvals + "/" + this.maxNumOfEvals
                  + " | Error:" + fun[0].round(MathContext.DECIMAL32));
        }

        mean = new BigDecimal[this.func.getNoOfInputs()];
        reflection = new BigDecimal[this.func.getNoOfInputs()];

        for (int i = 0; i < this.func.getNoOfInputs(); i++) {
          mean[i] = BigDecimal.ZERO;
          reflection[i] = BigDecimal.ZERO;
        }

        // Calculate mean
        for (int i = 0; i < this.func.getNoOfInputs(); i++) {
          for (int j = 0; j < (simplex.length - 1); j++) {
            mean[i] = mean[i].add(simplex[j][i]);
          }

          mean[i] = mean[i].divide(new BigDecimal(this.func.getNoOfInputs()),
              MathContext.DECIMAL64);
        }

        // Calculate reflection
        for (int i = 0; i < this.func.getNoOfInputs(); i++) {

          reflection[i] = this.alpha.add(new BigDecimal(1)).multiply(mean[i])
              .subtract(
                  this.alpha.multiply(simplex[this.func.getNoOfInputs()][i]));
        }

        try {
          fun_reflection = this.func.evaluate(reflection);
          this.numOfEvals++;
        } catch (final NonMatchingDesignSpace e) {
          System.out.println(e);
          return null;
        }

        if (fun[0].compareTo(fun_reflection) > 0) {

          expansion = new BigDecimal[this.func.getNoOfInputs()];

          // Calculate expansion
          for (int i = 0; i < this.func.getNoOfInputs(); i++) {

            expansion[i] = this.gamma.multiply(new BigDecimal("1"))
                .multiply(mean[i]).subtract(
                    this.gamma.multiply(simplex[this.func.getNoOfInputs()][i]));
          }

          try {
            fun_expansion = this.func.evaluate(expansion);
            this.numOfEvals++;
          } catch (final NonMatchingDesignSpace e) {
            return null;
          }

          if (fun_reflection.compareTo(fun_expansion) > 0) {

            if (this.verbose) {
              System.out.println("Expand: "
                  + fun[this.func.getNoOfInputs()].round(MathContext.DECIMAL32)
                  + "->" + fun_expansion.round(MathContext.DECIMAL32));
            }

            simplex[this.func.getNoOfInputs()] = expansion;
            fun[this.func.getNoOfInputs()] = fun_expansion;

          } else {

            if (this.verbose) {
              System.out.println("Reflect 1: "
                  + fun[this.func.getNoOfInputs()].round(MathContext.DECIMAL32)
                  + "->" + fun_reflection.round(MathContext.DECIMAL32));
            }

            simplex[this.func.getNoOfInputs()] = reflection;
            fun[this.func.getNoOfInputs()] = fun_reflection;

          }

        } else if (fun[this.func.getNoOfInputs() - 1]
            .compareTo(fun_reflection) > 0) {

          if (this.verbose) {
            System.out.println("Reflect 2 "
                + fun[this.func.getNoOfInputs()].round(MathContext.DECIMAL32)
                + "->" + fun_reflection.round(MathContext.DECIMAL32));
          }

          simplex[this.func.getNoOfInputs()] = reflection;
          fun[this.func.getNoOfInputs()] = fun_reflection;

        } else {

          contraction = new BigDecimal[this.func.getNoOfInputs()];

          // Calculate expansion
          for (int i = 0; i < this.func.getNoOfInputs(); i++) {

            if (fun[this.func.getNoOfInputs()].compareTo(fun_reflection) > 0) {

              contraction[i] = this.beta.multiply(mean[i])
                  .add(new BigDecimal("1").subtract(this.beta)
                      .multiply(reflection[i]));

            } else {

              contraction[i] = this.beta.multiply(mean[i])
                  .add(new BigDecimal("1").subtract(this.beta)
                      .multiply(simplex[this.func.getNoOfInputs()][i]));
            }
          }

          try {
            fun_contraction = this.func.evaluate(contraction);
            this.numOfEvals++;
          } catch (final NonMatchingDesignSpace e) {
            return null;
          }

          if (fun[this.func.getNoOfInputs()].compareTo(fun_contraction) > 0) {

            if (this.verbose) {
              System.out.println("Contract : "
                  + fun[this.func.getNoOfInputs()].round(MathContext.DECIMAL32)
                  + "->" + fun_contraction.round(MathContext.DECIMAL32));
            }

            simplex[this.func.getNoOfInputs()] = contraction;
            fun[this.func.getNoOfInputs()] = fun_contraction;

          } else {

            for (int i = 1; i < simplex.length; i++) {

              for (int j = 0; j < simplex[i].length; j++) {

                simplex[i][j] = this.sigma.multiply(simplex[0][j])
                    .add(new BigDecimal("1").subtract(this.sigma)
                        .multiply(simplex[i][j]));
              }

              try {
                fun[i] = this.func.evaluate(simplex[i]);
                this.numOfEvals++;
              } catch (final NonMatchingDesignSpace e) {
                return null;
              }
            }

            if (this.verbose) {
              System.out.println("Compress");
            }

          }
        }

        sort(simplex, fun);
      }

      this.result = simplex[0];
      this.error = fun[0];

    } else {
      System.err.println("Control Parameters are invalid");
      return null;
    }

    return this;
  }

  public BigDecimal getAlpha() {
    return this.alpha;
  }

  public void setAlpha(final BigDecimal alpha) {
    this.alpha = alpha;
  }

  public BigDecimal getBeta() {
    return this.beta;
  }

  public void setBeta(final BigDecimal beta) {
    this.beta = beta;
  }

  public BigDecimal getGamma() {
    return this.gamma;
  }

  public void setGamma(final BigDecimal gamma) {
    this.gamma = gamma;
  }

  public BigDecimal getSigma() {
    return this.sigma;
  }

  public void setSigma(final BigDecimal sigma) {
    this.sigma = sigma;
  }

  public BigDecimal[][] getInitialSimplex() {
    return this.initialSimplex;
  }

  public NelderMead setInitialSimplex(final BigDecimal[][] initialSimplex) {

    if ((initialSimplex.length == (this.func.getNoOfInputs() + 1))
        && (initialSimplex[0].length == this.func.getNoOfInputs())) {
      this.initialSimplex = initialSimplex;
      return this;
    } else {
      return null;
    }
  }

  private boolean checkControlParameters() {

    if ((this.alpha.compareTo(new BigDecimal("0")) > 0)
        && (this.gamma.compareTo(this.alpha) > 0)) {

      if ((this.beta.compareTo(new BigDecimal("0")) > 0)
          && (this.beta.compareTo(new BigDecimal("1")) < 0)) {

        if ((this.sigma.compareTo(new BigDecimal("0")) > 0)
            && (this.sigma.compareTo(new BigDecimal("1")) < 0)) {
          return true;
        } else {
          System.out.println("0 < sigma < 1 violated");
          return false;
        }
      } else {
        System.out.println("0 < beta < 1 violated");
        return false;
      }
    } else {
      System.out.println("0 < alpha < gamma violated");
      return false;
    }
  }

  private static void sort(final BigDecimal[][] simplex,
      final BigDecimal[] fun) {

    boolean noSwap = false;

    BigDecimal[] cpArray;
    BigDecimal cpFun;

    while (!noSwap) {

      noSwap = true;

      for (int i = 0; i < (fun.length - 1); i++) {

        if (fun[i + 1].compareTo(fun[i]) < 0) {

          cpArray = simplex[i];
          simplex[i] = simplex[i + 1];
          simplex[i + 1] = cpArray;

          cpFun = fun[i];
          fun[i] = fun[i + 1];
          fun[i + 1] = cpFun;

          noSwap = false;
        }
      }
    }
  }
}