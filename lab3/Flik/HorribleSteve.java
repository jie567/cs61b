public class HorribleSteve {
    public static void main(String [] args) {
        int i=0;
        for (int j=0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                continue; // break exits the for loop!  change as continue
            }
        }
        System.out.println("i is " + i);
    }
}
