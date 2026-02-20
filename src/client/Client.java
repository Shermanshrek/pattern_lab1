package client;

import proxy.CustomProxy;

class Client {
    public static void main(String[] args) {
        CustomProxy proxy = new CustomProxy(5000, "localhost");

        double[][] tests = {
                {2.5, 3.0},
                {-1.2, 5.5},
                {0.0, 100.5},
                {7.8, 0.0}
        };

        for (double[] test : tests) {
            try {
                double result = proxy.multiply(test[0], test[1]);
                System.out.printf("%f * %f = %f%n", test[0], test[1], result);
            } catch (Exception e) {
                System.err.printf("Ошибка для пары (%f, %f): %s%n", test[0], test[1], e.getMessage());
            }
        }
    }
}
