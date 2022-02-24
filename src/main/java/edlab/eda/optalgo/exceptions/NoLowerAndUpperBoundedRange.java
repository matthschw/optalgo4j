package edlab.eda.optalgo.exceptions;

import edlab.eda.optalgo.model.Function;

public class NoLowerAndUpperBoundedRange extends Exception {

  private static final long serialVersionUID = 5165713981450512151L;

  private Function function;

  public NoLowerAndUpperBoundedRange() {
  }

  public NoLowerAndUpperBoundedRange(final String message) {
    super(message);
  }

  public NoLowerAndUpperBoundedRange(final int idx) {
    super(buildMessage(null, idx));
  }

  public NoLowerAndUpperBoundedRange(Function function, final int idx) {
    super(buildMessage(function, idx));
    this.function = function;
  }

  public Function getFunction() {
    return this.function;
  }

  private static String buildMessage(Function function, final int idx) {

    if (function != null && function.getName() != null
        && !function.getName().isEmpty()) {
      return "No lower- and/or upper-bounded range is specified for function \""
          + function.getName() + "\" @ index= " + idx;
    } else {
      return "No lower- and/or upper-bounded range is specified @ index= "
          + idx;
    }
  }
}