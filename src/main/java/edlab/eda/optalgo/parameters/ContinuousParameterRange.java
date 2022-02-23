package edlab.eda.optalgo.parameters;

import java.math.BigDecimal;

/**
 * This class corresponds to a continuous parameter range
 */
public class ContinuousParameterRange extends ParameterRange {

  private BigDecimal lower = null;
  private BigDecimal upper = null;

  private ContinuousParameterRange(final BigDecimal lower,
      final BigDecimal upper) {
    this.lower = lower;
    this.upper = upper;
  }

  /**
   * Create a lower-bounded continuous parameter range
   * 
   * @param lower lower bound of the range
   * @return continuous parameter range
   */
  public static ContinuousParameterRange getLowerBoundedRange(
      final BigDecimal lower) {

    if (lower instanceof BigDecimal) {
      return new ContinuousParameterRange(lower, null);
    } else {
      return null;
    }

  }

  /**
   * Create a upper-bounded continuous parameter range
   * 
   * @param upper upper bound of the range
   * @return continuous parameter range
   */
  public static ContinuousParameterRange getUpperBoundedRange(
      final BigDecimal upper) {

    if (upper instanceof BigDecimal) {
      return new ContinuousParameterRange(null, upper);
    } else {
      return null;
    }

  }

  /**
   * Create a lower- and upper-bounded continuous parameter range
   * 
   * @param lower lower bound of the range
   * @param upper upper bound of the range
   * @return continuous parameter range
   */
  public static ContinuousParameterRange getParameterRange(
      final BigDecimal lower, final BigDecimal upper) {

    if ((lower instanceof BigDecimal) && (upper instanceof BigDecimal)) {

      if (lower.compareTo(upper) < 0) {
        return new ContinuousParameterRange(lower, upper);
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
      return value;
    }
  }

  @Override
  public boolean isValid(final BigDecimal value) {
    if ((this.lower == null) && (this.upper == null)) {
      return true;
    } else if ((this.lower == null) && (this.upper != null)) {
      return value.compareTo(this.upper) <= 0;
    } else if ((this.lower != null) && (this.upper == null)) {
      return value.compareTo(this.lower) >= 0;
    } else {
      return (value.compareTo(this.lower) >= 0)
          && (value.compareTo(this.upper) <= 0);
    }
  }

  @Override
  public BigDecimal getRandomValue() {

    if ((this.lower != null) && (this.upper != null)) {

      return this.lower.add(this.upper.subtract(this.lower)
          .multiply(new BigDecimal(Math.random())));

    } else {
      return null;
    }
  }
}