package edlab.eda.optalgo.algorithms.pso;

import java.math.BigDecimal;

public class Particle implements Comparable<Particle> {

  private final ParticleSwarmOptimization algo;

  private BigDecimal[] position;
  private final BigDecimal[] velocity;

  private BigDecimal[] bestPosition;
  private BigDecimal error;

  Particle(final ParticleSwarmOptimization algo, final BigDecimal[] position,
      final BigDecimal[] velocity) {
    this.algo = algo;
    this.position = ParticleSwarmOptimization.copy(position);
    this.bestPosition = ParticleSwarmOptimization.copy(position);
    this.velocity = ParticleSwarmOptimization.copy(velocity);
    this.error = new BigDecimal(Double.MAX_VALUE);
  }

  Particle(final ParticleSwarmOptimization algo, final BigDecimal[] position) {
    this.algo = algo;

    this.position = ParticleSwarmOptimization.copy(position);
    this.bestPosition = ParticleSwarmOptimization.copy(position);
    this.velocity = new BigDecimal[position.length];

    for (int i = 0; i < this.velocity.length; i++) {
      this.velocity[i] = BigDecimal.ZERO;
    }

    this.error = new BigDecimal(Double.MAX_VALUE);
  }

  Particle updateVelocity() {

    BigDecimal elem1;
    BigDecimal elem2;

    BigDecimal r1, r2;
    
    r1 = new BigDecimal(Math.random());
    r2 = new BigDecimal(Math.random());

    for (int i = 0; i < this.velocity.length; i++) {
      
      elem1 = this.bestPosition[i].subtract(this.position[i])
          .multiply(this.algo.getCognitive()).multiply(r1);

      elem2 = this.algo.getGlobalBest()[i].subtract(this.position[i])
          .multiply(this.algo.getSocial()).multiply(r2);

      this.velocity[i] = this.algo.getMomentum().multiply(this.velocity[i])
          .add(elem1).add(elem2);
    }

    return this;
  }

  Particle evaluate() {

    final BigDecimal new_error = this.algo.evaluate(this.position);

    if (new_error.compareTo(this.error) < 0) {
      this.error = new_error;
      this.bestPosition = ParticleSwarmOptimization.copy(this.position);
    }

    return this;
  }

  Particle updatePosition() {

    for (int i = 0; i < this.position.length; i++) {
      this.position[i] = this.position[i].add(this.velocity[i]);
    }

    if (!this.algo.isValid(this.position)) {
      this.position = this.algo.clip(this.position);
    }

    return this;
  }

  @Override
  public int compareTo(final Particle o) {
    return this.error.compareTo(o.error);
  }

  public BigDecimal[] getPosition() {
    return this.position;
  }
}