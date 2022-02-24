package edlab.eda.optalgo;

import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.Test;

import edlab.eda.optalgo.algorithms.pso.ParticleSwarmOptimization;
import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.testfuns.Himmelblau;
import edlab.eda.optalgo.testfuns.Rastrigin;
import edlab.eda.optalgo.testfuns.Rosenbrock;
import edlab.eda.optalgo.testfuns.Sphere;

class ParticleSwarmOptimizationTest {

  private static boolean VERBOSE = true;
  private static int NUM_OF_PARTICLES = 10;
  private static int NUM_OF_RUNS = 200;
  private static int MAX_EVALUATIONS = 50000;
  private static BigDecimal ERROR = new BigDecimal("1E-3");

  @Test
  void test() {
    ParticleSwarmOptimizationTest.rosenbrock();
    ParticleSwarmOptimizationTest.rastrigin();
    ParticleSwarmOptimizationTest.himmelblau();
    ParticleSwarmOptimizationTest.sphere();
  }

  private static void himmelblau() {
    final Himmelblau himmelblau = new Himmelblau();
    ParticleSwarmOptimization pso;
    BigDecimal error;

    for (int i = 0; i < NUM_OF_RUNS; i++) {

      pso = new ParticleSwarmOptimization(himmelblau, MAX_EVALUATIONS,
          NUM_OF_PARTICLES, ERROR);
      pso.setVerbose(VERBOSE);

      pso.run();

      try {

        error = himmelblau.evaluate(pso.getResult());

        if (error.compareTo(ERROR) > 0) {

          fail("No convergence for \"Rastrigin\" in iteration " + i
              + " with error " + error.round(MathContext.DECIMAL32));

        } else if (VERBOSE) {

          System.out.println("\nResult for \"Rosenbrock\" achieved:");

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

  private static void rastrigin() {
    final Rastrigin rastrigin = new Rastrigin(2, new BigDecimal("1"));

    ParticleSwarmOptimization pso;
    BigDecimal error;

    for (int i = 0; i < NUM_OF_RUNS; i++) {

      pso = new ParticleSwarmOptimization(rastrigin, MAX_EVALUATIONS,
          NUM_OF_PARTICLES, ERROR);
      pso.setVerbose(VERBOSE);

      pso.run();

      try {

        error = rastrigin.evaluate(pso.getResult());

        if (error.compareTo(ERROR) > 0) {

          fail("No convergence for \"Rastrigin\" in iteration " + i
              + " with error " + error.round(MathContext.DECIMAL32));

        } else if (VERBOSE) {

          System.out.println("\nResult for \"Rosenbrock\" achieved:");

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

  private static void rosenbrock() {

    final Rosenbrock rosenbrock = new Rosenbrock(2);

    ParticleSwarmOptimization pso;
    BigDecimal error;

    for (int i = 0; i < NUM_OF_RUNS; i++) {

      pso = new ParticleSwarmOptimization(rosenbrock, MAX_EVALUATIONS,
          NUM_OF_PARTICLES, ERROR);
      pso.setVerbose(VERBOSE);

      pso.run();

      try {

        error = rosenbrock.evaluate(pso.getResult());

        if (error.compareTo(ERROR) > 0) {

          fail("No convergence for \"Rosenbrock\" in iteration " + i
              + " with error " + error.round(MathContext.DECIMAL32));

        } else if (VERBOSE) {

          System.out.println("\nResult for \"Rosenbrock\" achieved:");

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
  
  private static void sphere() {

    final Sphere sphere = new Sphere(2);

    ParticleSwarmOptimization pso;
    BigDecimal error;

    for (int i = 0; i < NUM_OF_RUNS; i++) {

      pso = new ParticleSwarmOptimization(sphere, MAX_EVALUATIONS,
          NUM_OF_PARTICLES, ERROR);
      pso.setVerbose(VERBOSE);

      pso.run();

      try {

        error = sphere.evaluate(pso.getResult());

        if (error.compareTo(ERROR) > 0) {

          fail("No convergence for \"Sphere\" in iteration " + i
              + " with error " + error.round(MathContext.DECIMAL32));

        } else if (VERBOSE) {

          System.out.println("\nResult for \"Sphere\" achieved:");

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