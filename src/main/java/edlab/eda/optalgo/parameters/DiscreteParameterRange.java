package edlab.eda.optalgo.parameters;

import java.math.BigDecimal;
import java.math.RoundingMode;

import edlab.eda.optalgo.exceptions.NoLowerAndUpperBoundedRange;

/**
 * This class corresponds to a discrete parameter range
 *
 */
public class DiscreteParameterRange extends ParameterRange {

  private BigDecimal lower = null;
  private BigDecimal upper = null;
  private BigDecimal grid = null;

  private DiscreteParameterRange(final BigDecimal lower, final BigDecimal upper,
      final BigDecimal grid) {
    this.lower = lower;
    this.upper = upper;
    this.grid = grid;
  }

  /**
   * Create a lower-bounded discrete parameter range
   * 
   * @param lower lower bound of the range
   * @param grid  discrete step
   * @return discrete parameter range
   */
  public static DiscreteParameterRange getLowerBoundedRange(
      final BigDecimal lower, final BigDecimal grid) {

    if ((lower instanceof BigDecimal) && (grid instanceof BigDecimal)
        && (grid.compareTo(BigDecimal.ZERO) > 0)) {
      return new DiscreteParameterRange(lower, null, grid);
    } else {
      return null;
    }
  }

  /**
   * Create a upper-bounded discrete parameter range
   * 
   * @param upper upper bound of the range
   * @param grid  discrete step
   * @return discrete parameter range
   */
  public static DiscreteParameterRange getUpperBoundedRange(
      final BigDecimal upper, final BigDecimal grid) {

    if ((upper instanceof BigDecimal) && (grid instanceof BigDecimal)
        && (grid.compareTo(BigDecimal.ZERO) > 0)) {
      return new DiscreteParameterRange(null, upper, grid);
    } else {
      return null;
    }

  }

  /**
   * Create a lower- and upper-bounded discrete parameter range
   * 
   * @param lower lower bound of the range
   * @param upper upper bound of the range
   * @param grid  discrete step
   * @return discrete parameter range
   */
  public static DiscreteParameterRange getParameterRange(final BigDecimal lower,
      final BigDecimal upper, final BigDecimal grid) {

    if ((lower instanceof BigDecimal) && (upper instanceof BigDecimal)
        && (grid instanceof BigDecimal)
        && (grid.compareTo(BigDecimal.ZERO) > 0)) {

      if (lower.compareTo(upper) < 0) {
        return new DiscreteParameterRange(lower, upper, grid);
      } else {
        return null;
      }

    } else {
      return null;
    }
  }

  @Override
  public BigDecimal getClosestValidValue(final BigDecimal value) {

    if ((this.lower != null) && (value.compareTo(this.lower) <= 0)) {
      return this.lower;
    } else if ((this.upper != null) && (value.compareTo(this.upper) >= 0)) {
      return this.upper;
    } else {

      if (this.lower != null) {

        return value.subtract(this.lower).divide(this.grid)
            .setScale(0, RoundingMode.HALF_UP).multiply(this.grid)
            .add(this.lower);

      } else if (this.upper != null) {
        return this.upper.subtract(this.upper.subtract(value).divide(this.grid)
            .setScale(0, RoundingMode.HALF_UP).multiply(this.grid));
      }

      return null;
    }
  }

  @Override
  public boolean isValid(final BigDecimal value) {
    return false;
  }

  @Override
  public BigDecimal getRandomValue() throws NoLowerAndUpperBoundedRange {

    if ((this.lower != null) && (this.upper != null)) {

      return this.getClosestValidValue(this.lower.add(this.upper
          .subtract(this.lower).multiply(new BigDecimal(Math.random()))));

    } else {
      throw new NoLowerAndUpperBoundedRange();
    }
  }
}