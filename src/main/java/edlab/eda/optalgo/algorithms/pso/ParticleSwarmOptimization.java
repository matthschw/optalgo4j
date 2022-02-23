package edlab.eda.optalgo.algorithms.pso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

import edlab.eda.optalgo.exceptions.NoLowerAndUpperBoundedRange;
import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.model.UnivariateFunction;
import edlab.eda.optalgo.model.UnivariateOptimizer;

public class ParticleSwarmOptimization extends UnivariateOptimizer {

  private ArrayList<Particle> particles;

  private BigDecimal momentum = new BigDecimal("2");
  private BigDecimal cognitive = new BigDecimal("1.5");
  private BigDecimal social = new BigDecimal("1.0");

  private BigDecimal[] globalBest;
  private BigDecimal error = new BigDecimal(Double.MAX_VALUE);

  private int numOfMaxEvals;
  private int numOfParticles;

  public ParticleSwarmOptimization(final UnivariateFunction func,
      final int maxNumOfEvals, int numOfParticles) {

    super(func, maxNumOfEvals);

    try {
      this.globalBest = func.getRandomPosition();
    } catch (NoLowerAndUpperBoundedRange e) {
      this.globalBest = new BigDecimal[func.getNoOfInputs()];
    }

    this.numOfMaxEvals = maxNumOfEvals;
    this.numOfParticles = numOfParticles;

    particles = new ArrayList<Particle>();

  }

  public ParticleSwarmOptimization init() {

    Particle particle;

    for (int i = 0; i < this.numOfParticles; i++) {

      try {
        particle = new Particle(this, this.func.getRandomPosition(),
            globalBest);
      } catch (NoLowerAndUpperBoundedRange e) {
        return null;
      }

      particles.add(particle);

      particle.evaluate();
    }

    return this;
  }

  public BigDecimal evaluate(BigDecimal[] values) {

    try {

      BigDecimal retval = this.func.evaluate(values);

      if (retval.compareTo(error) > 0) {
        this.globalBest = values;
      }

      return retval;
    } catch (NonMatchingDesignSpace e) {
      return null;
    }
  }

  public ParticleSwarmOptimization position(Particle p) {
    return null;
  }

  public BigDecimal[] getGlobalBest() {
    return this.globalBest;
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

  @Override
  public void run() {
    // TODO Auto-generated method stub

  }

}
