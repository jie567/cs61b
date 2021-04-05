import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {


    @Test
    //@source
    public void TestStudentOne(){
        StudentArrayDeque<Integer> std = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads=new ArrayDequeSolution<>();
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                std.addLast(i);
            } else {
                std.addFirst(i);
            }
        }

            while(!std.isEmpty()){
            double numberBetweenZeroAndTen = StdRandom.uniform(0,10);
            if (numberBetweenZeroAndTen < 5) {
                Integer expected=std.get(std.size()-1);
                Integer actual = std.removeLast();
                assertEquals("Oh noooo!\nThis is bad:\n last " + actual
                        + " not equal to removelast " + expected + "!", expected, actual);
            } else {
                Integer expected=std.get(0);
                Integer actual = std.removeFirst();
                assertEquals("Oh noooo!\nThis is bad:\n first " + actual
                        + " not equal to removefirst " + expected + "!", expected, actual);
            }
        }
    }
}
