package dev.narlotl;

import java.util.Random;

public class rand {

    static Random random = new Random();
    public static int fromTo(int floor, int ceiling) {
        int result;
        if (ceiling - floor > 0) {
            result = random.nextInt(ceiling - floor) + floor;
        }
        else {
            result = 0;
            System.out.println("That is an invalid input, FLOOR must be less than CEILING.");
        }
        return result;
    }
}
