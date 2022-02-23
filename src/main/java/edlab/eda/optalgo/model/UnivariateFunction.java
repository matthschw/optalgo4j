package edlab.eda.optalgo.model;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NoLowerAndUpperBoundedRange;
import edlab.eda.optalgo.exceptions.NonMatchingDesignSpace;
import edlab.eda.optalgo.parameters.ParameterRange;

public abstract class UnivariateFunction {

  protected int noOfInputs;
  protected ParameterRange[] range;

  public UnivariateFunction(final int noOfInputs) {
    this.noOfInputs = noOfInputs;
    this.range = new ParameterRange[noOfInputs];
  }

  public abstract BigDecimal evaluate(BigDecimal[] vals)
      throws NonMatchingDesignSpace;

  public int getNoOfInputs() {
    return this.noOfInputs;
  }

  public BigDecimal[] getRandomPosition() throws NoLowerAndUpperBoundedRange {

    final BigDecimal[] retval = new BigDecimal[this.noOfInputs];

    for (int i = 0; i < retval.length; i++) {

      try {
        retval[i] = this.range[i].getRandomValue();
      } catch (final Exception e) {
        throw new NoLowerAndUpperBoundedRange(i);
      }
    }

    return retval;
  }

  public boolean setParameterRange(final int index, final ParameterRange range)
      throws NonMatchingDesignSpace {
    if ((index >= 0) && (index < this.range.length)) {
      this.range[index] = range;
      return true;
    } else {
      throw new NonMatchingDesignSpace(index, this.range.length);
    }
  }

  public ParameterRange getParameterRange(final int index)
      throws NonMatchingDesignSpace {
    if ((index >= 0) && (index < this.range.length)) {
      return this.range[index];
    } else {
      throw new NonMatchingDesignSpace(index, index);
    }
  }

  public boolean isValid(final BigDecimal[] values) throws NonMatchingDesignSpace {

    if (values.length == this.getNoOfInputs()) {

      for (int i = 0; i < values.length; i++) {

        if (this.range[i] != null) {
          if (!this.range[i].isValid(values[i])) {
            return false;
          }
        }
      }
      return true;
    } else {
      throw new NonMatchingDesignSpace(values.length, this.getNoOfInputs());
    }
  }

  public BigDecimal[] getClosestValidValues(final BigDecimal[] values)
      throws NonMatchingDesignSpace {

    if (values.length == this.getNoOfInputs()) {

      final BigDecimal[] retval = new BigDecimal[values.length];

      for (int i = 0; i < values.length; i++) {

        if (this.range[i] == null) {
          retval[i] = values[i];
        } else {
          retval[i] = this.range[i].getClosestValidValue(values[i]);
        }
      }
      return retval;
    } else {
      throw new NonMatchingDesignSpace(values.length, this.getNoOfInputs());
    }
  }

}