package edlab.eda.optalgo.algorithms;

import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.Test;

import edlab.eda.optalgo.algorithms.pso.ParticleSwarmOptimization;
import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.testfuns.Rosenbrock;

class ParticleSwarmOptimizationTest {

  private static boolean VERBOSE = false;
  private static int NUM_OF_PARTICLES = 10;
  private static int NUM_OF_RUNS = 100;
  private static int MAX_EVALUATIONS = 400;
  private static BigDecimal ERROR = new BigDecimal("1");

  @Test
  void test() {
    ParticleSwarmOptimizationTest.rosenbrock();
  }

  private static void rosenbrock() {

    final Rosenbrock rosenbrock = new Rosenbrock(2);

    ParticleSwarmOptimization pso;
    BigDecimal error;

    for (int i = 0; i < NUM_OF_RUNS; i++) {

      pso = new ParticleSwarmOptimization(rosenbrock, MAX_EVALUATIONS,
          NUM_OF_PARTICLES, ERROR);

      pso.run();

      try {

        error = rosenbrock.evaluate(pso.getResult());

        if (error.compareTo(ERROR) > 0) {
          
          fail("No convergence for \"Rosenbrock\" in iteration " + i);
          
        } else if (VERBOSE) {
          
          System.out.println("\nResult for \"Himmelblau\" achieved:");
          
          for (int j = 0; j < pso.getResult().length; j++) {
            System.out.println("x_" + j + "="
                + pso.getResult()[j].round(MathContext.DECIMAL32));
          }
        }

      } catch (final NonMatchingDesignSpace e) {
        e.printStackTrace();
      }

    }
  }
}
