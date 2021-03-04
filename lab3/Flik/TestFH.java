import org.junit.Test;
import static org.junit.Assert.*;

public class TestFH {

    @Test
    public void TestFlik(){
        int a=1,b=1;
        assertTrue(Flik.isSameNumber(a,b));
    }
    @Test
    public void TestHorribleSteve(){
        String output="i is 500";

    }
    public static void main(String... args) {
        jh61b.junit.TestRunner.runTests("all", TestFH.class);
    }
}
