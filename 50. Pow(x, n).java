//Implement pow(x, n), which calculates x raised to the power n (i.e. xn).
//
//
//
//        Example 1:
//
//        Input: x = 2.00000, n = 10
//        Output: 1024.00000
//        Example 2:
//
//        Input: x = 2.10000, n = 3
//        Output: 9.26100

class Solution50 {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / (x * myPow(x, -(n + 1)));
        }
        double temp = myPow(x, n / 2);
        return n % 2 == 0? temp * temp: temp * temp * x;

    }
}
