package edlab.eda.optalgo.exceptions;

public class NoLowerAndUpperBoundedRange extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public NoLowerAndUpperBoundedRange() {
  }

  public NoLowerAndUpperBoundedRange(final String message) {
    super(message);
  }

  public NoLowerAndUpperBoundedRange(final int idx) {
    super("No lower- and upper-bounded range is speciefied for index= " + idx);
  }

}
