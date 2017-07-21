package billmortam;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 * Created by pp00344204 on 30/04/17.
 */
public class TimeComplexityTestCase {
    private long startedTime;
    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() throws Exception {
        startedTime = System.currentTimeMillis();
        System.out.println("-------------------------------------");
        System.out.println("Started time: "+ startedTime +" ms");
        System.out.println(testName.getMethodName());
    }

    @After
    public void tearDown() throws Exception {
        long endTime = System.currentTimeMillis();
        System.out.println("End time: " + endTime + " ms");
        endTime = System.currentTimeMillis() - startedTime;
        System.out.println("Total time taken: "+ endTime + " ms");

    }
}
