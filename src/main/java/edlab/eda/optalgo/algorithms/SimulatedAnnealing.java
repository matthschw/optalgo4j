package edlab.eda.optalgo.algorithms;

import java.math.BigDecimal;

import edlab.eda.optalgo.exceptions.NoLowerAndUpperBoundedRange;
import edlab.eda.optalgo.model.UnivariateFunction;
import edlab.eda.optalgo.model.UnivariateOptimizer;

public class SimulatedAnnealing extends UnivariateOptimizer{

  public SimulatedAnnealing(final UnivariateFunction func) {
    super(func);
  }

  @Override
  public SimulatedAnnealing run() {
    
    try {
      
      @SuppressWarnings("unused")
      final BigDecimal[] x = this.func.getRandomPosition();
      
    } catch (final NoLowerAndUpperBoundedRange e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    // TODO Auto-generated method stub
    return this;
  }

}
