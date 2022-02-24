package edlab.eda.optalgo;

import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.Test;

import edlab.eda.optalgo.algorithms.NelderMead;
import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.testfuns.Himmelblau;
import edlab.eda.optalgo.testfuns.Rastrigin;
import edlab.eda.optalgo.testfuns.Rosenbrock;
import edlab.eda.optalgo.testfuns.Sphere;

class NelderMeadTest {

  private static boolean VERBOSE = false;
  private static int NUM_OF_RUNS = 1000;
  private static int MAX_ITERATIONS = 10000;
  private static BigDecimal ERROR = new BigDecimal("1e-3");

  @Test
  void testRun() {
    NelderMeadTest.himmelblau();
    NelderMeadTest.rastrigin();
    NelderMeadTest.rosenbrock();
    NelderMeadTest.sphere();
  }

  public static void himmelblau() {

    final Himmelblau himmelblau = new Himmelblau();

    NelderMead nm;
    BigDecimal error;

    for (int i = 0; i < NUM_OF_RUNS; i++) {

      nm = new NelderMead(himmelblau);
      nm.setVerbose(VERBOSE);
      nm.setMaxError(ERROR);
      nm.setNumOfMaxEvals(MAX_ITERATIONS);

      nm.run();

      try {

        error = himmelblau.evaluate(nm.getResult());

        if (error.compareTo(ERROR) > 0) {
          fail("No convergence for Himmelbau in iteration " + i);
        } else if (VERBOSE) {
          System.out.println("\nResult for \"Himmelblau\" achieved:");
          for (int j = 0; j < nm.getResult().length; j++) {
            System.out.println("x_" + j + "="
                + nm.getResult()[j].round(MathContext.DECIMAL32));
          }
        }

      } catch (final NonMatchingDesignSpace e) {
        e.printStackTrace();
      }
    }
  }

  public static void rastrigin() {
    final Rastrigin rastrigin = new Rastrigin(2, new BigDecimal("1"));

    NelderMead nm;
    BigDecimal error;

    for (int i = 0; i < NUM_OF_RUNS; i++) {

      nm = new NelderMead(rastrigin);
      nm.setVerbose(VERBOSE);
      nm.setMaxError(ERROR);
      nm.setNumOfMaxEvals(MAX_ITERATIONS);

      nm.run();

      try {

        error = rastrigin.evaluate(nm.getResult());

        if (error.compareTo(ERROR) > 0) {
          fail("No convergence for \"Rastrigin\" in iteration " + i);
        } else if (VERBOSE) {
          System.out.println("\nResult for \"Rastrigin\" achieved:");
          for (int j = 0; j < nm.getResult().length; j++) {
            System.out.println("x_" + j + "="
                + nm.getResult()[j].round(MathContext.DECIMAL32));
          }
        }
      } catch (final NonMatchingDesignSpace e) {
        e.printStackTrace();
      }
    }
  }

  public static void rosenbrock() {
    final Rosenbrock rosenbrock = new Rosenbrock(2);

    NelderMead nm;
    BigDecimal error;

    for (int i = 0; i < NUM_OF_RUNS; i++) {

      nm = new NelderMead(rosenbrock);
      nm.setVerbose(VERBOSE);
      nm.setMaxError(ERROR);
      nm.setNumOfMaxEvals(MAX_ITERATIONS);

      nm.run();

      try {

        error = rosenbrock.evaluate(nm.getResult());

        if (error.compareTo(ERROR) > 0) {
          fail("No convergence for Rosenbrock in iteration " + i);
        } else if (VERBOSE) {
          System.out.println("\nResult for \"Rosenbrock\" achieved:");
          for (int j = 0; j < nm.getResult().length; j++) {
            System.out.println("x_" + j + "="
                + nm.getResult()[j].round(MathContext.DECIMAL32));
          }
        }
      } catch (final NonMatchingDesignSpace e) {
        e.printStackTrace();
      }
    }
  }
  public static void sphere() {
    final Sphere sphere = new Sphere(2);

    NelderMead nm;
    BigDecimal error;

    for (int i = 0; i < NUM_OF_RUNS; i++) {

      nm = new NelderMead(sphere);
      nm.setVerbose(VERBOSE);
      nm.setMaxError(ERROR);
      nm.setNumOfMaxEvals(MAX_ITERATIONS);

      nm.run();

      try {

        error = sphere.evaluate(nm.getResult());

        if (error.compareTo(ERROR) > 0) {
          fail("No convergence for Sphere in iteration " + i);
        } else if (VERBOSE) {
          System.out.println("\nResult for \"Sphere\" achieved:");
          for (int j = 0; j < nm.getResult().length; j++) {
            System.out.println("x_" + j + "="
                + nm.getResult()[j].round(MathContext.DECIMAL32));
          }
        }
      } catch (final NonMatchingDesignSpace e) {
        e.printStackTrace();
      }
    }
  }
}