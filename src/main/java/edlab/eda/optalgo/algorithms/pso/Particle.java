package edlab.eda.optalgo.algorithms.pso;

import java.math.BigDecimal;

public class Particle implements Comparable<Particle> {

  private ParticleSwarmOptimization algo;

  private BigDecimal[] position;
  private BigDecimal[] bestPosition;
  private BigDecimal[] velocity;

  private BigDecimal error;

  public Particle(ParticleSwarmOptimization algo, BigDecimal[] position,
      BigDecimal[] velocity) {
    this.algo = algo;
    this.position = position;
    this.bestPosition = position;
    this.velocity = velocity;
    this.error = new BigDecimal(Math.random() * Double.MAX_VALUE);

  }

  Particle updateVelocity() {

    BigDecimal elem1;
    BigDecimal elem2;

    for (int i = 0; i < velocity.length; i++) {

      elem1 = bestPosition[i].subtract(position[i])
          .multiply(algo.getCognitive())
          .multiply(new BigDecimal(Math.random()));

      elem2 = algo.getGlobalBest()[i].subtract(position[i])
          .multiply(algo.getSocial()).multiply(new BigDecimal(Math.random()));

      velocity[i] = algo.getMomentum().multiply(velocity[i]).add(elem1)
          .add(elem2);
    }

    return this;
  }

  Particle evaluate() {

    BigDecimal newError = this.algo.evaluate(position);

    if (newError.compareTo(error) > 0) {
      this.error = newError;
      this.bestPosition = position;
    }

    return this;
  }

  Particle updatePosition() {

    for (int i = 0; i < position.length; i++) {
      position[i] = position[i].add(position[i]);
    }

    return this;
  }

  @Override
  public int compareTo(Particle o) {
    return this.error.compareTo(o.error);
  }
}