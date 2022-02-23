package edlab.eda.optalgo.parameters;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NoLowerAndUpperBoundedRange;

/**
 * This class corresponds to a parameter range
 *
 */
public abstract class ParameterRange {

  /**
   * Get the closest valid value in a range for a given value
   * 
   * @param value original value
   * @return closest valid value
   */
  public abstract BigDecimal getClosestValidValue(BigDecimal value);

  /**
   * Get random parameter in range
   * 
   * @return random value from range
   * @throws NoLowerAndUpperBoundedRange when no lower and upper bound is
   *                                     specified for a range
   */
  public abstract BigDecimal getRandomValue()
      throws NoLowerAndUpperBoundedRange;

  /**
   * Check if <code>value</code> is a valid value in the range
   * 
   * @param value value to be checked
   * @return <code>true</code> if value is valid, <code>false</code> otherwise
   */
  public abstract boolean isValid(BigDecimal value);
}