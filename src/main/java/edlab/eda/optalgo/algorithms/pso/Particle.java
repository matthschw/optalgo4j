package edlab.eda.optalgo.algorithms.pso;

import java.math.BigDecimal;
import java.math.MathContext;

public class Particle implements Comparable<Particle> {

  private final ParticleSwarmOptimization algo;

  private BigDecimal[] position;
  private BigDecimal[] bestPosition;
  private final BigDecimal[] velocity;

  private BigDecimal error;

  public Particle(final ParticleSwarmOptimization algo,
      final BigDecimal[] position, final BigDecimal[] velocity) {
    this.algo = algo;
    this.position = position;
    this.bestPosition = position;
    this.velocity = velocity;
    this.error = new BigDecimal(Double.MAX_VALUE);
  }

  public Particle(final ParticleSwarmOptimization algo,
      final BigDecimal[] position) {
    this.algo = algo;
    this.position = position;
    this.bestPosition = position;
    this.velocity = new BigDecimal[position.length];

    System.out.print("POS ");

    for (int i = 0; i < this.velocity.length; i++) {
      this.velocity[i] = BigDecimal.ZERO;

      System.out.print(this.position[i].round(MathContext.DECIMAL32));
      System.out.print(" ");

    }
    System.out.println(" ");

    this.error = new BigDecimal(Double.MAX_VALUE);
  }

  Particle updateVelocity() {

    BigDecimal elem1;
    BigDecimal elem2;

    for (int i = 0; i < this.velocity.length; i++) {

      elem1 = this.bestPosition[i].subtract(this.position[i])
          .multiply(this.algo.getCognitive())
          .multiply(new BigDecimal(Math.random()));

      elem2 = this.algo.getGlobalBest()[i].subtract(this.position[i])
          .multiply(this.algo.getSocial())
          .multiply(new BigDecimal(Math.random()));

      this.velocity[i] = this.algo.getMomentum().multiply(this.velocity[i])
          .add(elem1).add(elem2);
    }

    return this;
  }

  Particle evaluate() {

    final BigDecimal newError = this.algo.evaluate(this.position);

    // System.out.println("1X:" + newError.round(MathContext.DECIMAL32)+ "-"
    // + this.error.round(MathContext.DECIMAL32));
    if (newError.compareTo(this.error) < 0) {
      // System.out.println("2X:" + newError.round(MathContext.DECIMAL32) + "-"
      // + this.error.round(MathContext.DECIMAL32));
      this.error = newError;
      this.bestPosition = this.position;
    }

    return this;
  }

  Particle updatePosition() {

    for (int i = 0; i < this.position.length; i++) {
      this.position[i] = this.position[i].add(this.velocity[i]);

      System.out.println(this + " x_" + i + "="
          + this.position[i].round(MathContext.DECIMAL32));
    }

    position = this.algo.clip(position);

    return this;
  }

  @Override
  public int compareTo(final Particle o) {
    return this.error.compareTo(o.error);
  }
}