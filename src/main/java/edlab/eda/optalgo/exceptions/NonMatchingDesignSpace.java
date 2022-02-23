package edlab.eda.optalgo.exceptions;

public class NonMatchingDesignSpace extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public NonMatchingDesignSpace(final String message) {
    super(message);
  }

  public NonMatchingDesignSpace(final int is, final int target) {
    super("Design Space has a size of " + target + " but index is " + is);
  }

}
