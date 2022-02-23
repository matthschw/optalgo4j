package edlab.eda.optalgo.algorithms.pso;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import edlab.eda.optalgo.exceptions.NoLowerAndUpperBoundedRange;
import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.model.UnivariateFunction;
import edlab.eda.optalgo.model.UnivariateOptimizer;

public class ParticleSwarmOptimization extends UnivariateOptimizer {

  private ArrayList<Particle> particles = new ArrayList<>();

  private final BigDecimal momentum = new BigDecimal("0.75");
  private final BigDecimal cognitive = new BigDecimal("1.5");
  private final BigDecimal social = new BigDecimal("2.0");

  private BigDecimal error = new BigDecimal(Double.MAX_VALUE);

  private final int numOfParticles;

  public ParticleSwarmOptimization(final UnivariateFunction func,
      final int maxNumOfEvals, final int numOfParticles,
      final BigDecimal maxError) {

    super(func, maxNumOfEvals, maxError);

    try {
      this.result = this.func.getRandomPosition();
    } catch (final NoLowerAndUpperBoundedRange e) {
      this.result = new BigDecimal[func.getNoOfInputs()];
    }

    this.numOfParticles = numOfParticles;
  }

  private ParticleSwarmOptimization init() {

    Particle particle;
    this.particles = new ArrayList<>();

    for (int i = 0; i < this.numOfParticles; i++) {

      try {
        particle = new Particle(this, this.func.getRandomPosition());
      } catch (final NoLowerAndUpperBoundedRange e) {
        return null;
      }

      this.particles.add(particle);
    }

    return this;
  }

  BigDecimal evaluate(final BigDecimal[] position) {

    try {

      final BigDecimal retval = this.func.evaluate(position);
      this.numOfEvals++;

      if (retval.compareTo(this.error) < 0) {

        if (this.verbose) {
          System.out.println(
              "Update best point :" + this.error.round(MathContext.DECIMAL32));
        }

        this.result = ParticleSwarmOptimization.copy(position);
        this.error = retval;
      }

      return retval;

    } catch (final NonMatchingDesignSpace e) {
      return null;
    }
  }

  public BigDecimal[] getGlobalBest() {
    return this.result;
  }

  public BigDecimal getMomentum() {
    return this.momentum;
  }

  public BigDecimal getCognitive() {
    return this.cognitive;
  }

  public BigDecimal getSocial() {
    return this.social;
  }

  boolean isValid(BigDecimal[] pos) {
    try {
      return this.func.isValid(pos);
    } catch (NonMatchingDesignSpace e) {
      return false;
    }
  }

  BigDecimal[] clip(BigDecimal[] pos) {
    try {
      return this.func.getClosestValidValues(pos);
    } catch (NonMatchingDesignSpace e) {
      return null;
    }
  }

  @Override
  public UnivariateOptimizer run() {

    this.init();

    this.numOfEvals = 0;

    while ((this.error.compareTo(this.maxError) > 0)
        && (this.numOfEvals < this.maxNumOfEvals)) {

      for (final Particle particle : this.particles) {
        particle.updateVelocity().updatePosition().evaluate();
      }
    }

    return this;
  }

  static BigDecimal[] copy(BigDecimal[] array) {

    BigDecimal[] retval = new BigDecimal[array.length];

    for (int i = 0; i < retval.length; i++) {
      retval[i] = new BigDecimal(array[i].toString());
    }

    return retval;
  }
}