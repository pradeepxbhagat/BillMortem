import billmortam.util.Calculator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class CalculatorTest {

    @Test
    public void float_number_addition(){
        float[] nums = {1.0f,2.0f,3.5f};
        double total = Calculator.addArrayElement(nums);

        double actual = 6.5f;
        Assert.assertEquals(actual,total,0.0f);
    }
}
