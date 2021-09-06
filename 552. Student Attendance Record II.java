/*
An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:

'A': Absent.
'L': Late.
'P': Present.
Any student is eligible for an attendance award if they meet both of the following criteria:

The student was absent ('A') for strictly fewer than 2 days total.
The student was never late ('L') for 3 or more consecutive days.
Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.



Example 1:

Input: n = 2
Output: 8
Explanation: There are 8 records with length 2 that are eligible for an award:
"PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
Example 2:

Input: n = 1
Output: 3
Example 3:

Input: n = 10101
Output: 183236316


Constraints:

1 <= n <= 105
 */

class Solution552 {
    public int checkRecord(int n) {
        if (n == 1) return 3;
        long modular = (long) 1e9 + 7;
        long[][] ret = new long[5][n+1];
        for (int row = 0; row < 4; row++) {
            ret[row][2] = 1;
        }
        ret[4][2] = 4;
        ret[4][1] = 2;
        ret[4][0] = 1;
        for (int col = 3; col <= n; col++) {
            ret[0][col] = (ret[0][col-1] + ret[1][col-1]) % modular;
            ret[1][col] = (ret[2][col-1] + ret[3][col-1]) % modular;
            ret[2][col] = (ret[0][col-1] + ret[1][col-1]) % modular;
            ret[3][col] = ret[2][col-1];
            ret[4][col] = (ret[0][col] + ret[1][col] + ret[2][col] + ret[3][col]) % modular;
        }

        long count = 0;
        for (int col = 0; col <= n; col++) {
            int leftNumber = Math.max(0, col - 1);
            int rightNumber = n - col;
            long partial = (ret[4][leftNumber] % modular) * (ret[4][rightNumber] % modular);
            long curr = (long) (partial % modular);
            count = (count + curr) % modular;
        }
        return (int) (count % modular);


    }
}

// State transition
// state[0] : end with A
// state[1] : end with P and 0 A before end;
// state[2] : end with P and 1 A before end;
// state[3] : end with 1 L and 0 A before end;
// state[4] : end with 1 L and 1 A before end;
// state[5] : end with 2 L and 0 A before end;
// state[6] : end with 2 L and 1 A before end;
class Solution552a {
    private static final int MOD = 1000000007;

    public int checkRecord(int n) {

        long[] prevState = new long[] {1L, 1L, 0L, 1L, 0L, 0L, 0L};

        for(int i = 2; i <= n; i++) {
            long[] currState = new long[7];

            currState[0] = ((prevState[1] + prevState[3]) % MOD + prevState[5]) % MOD;

            currState[1] = currState[0];

            currState[2] = (((prevState[0] + prevState[2]) % MOD + prevState[4]) % MOD + prevState[6]) % MOD;

            currState[3] = prevState[1];

            currState[4] = (prevState[0] + prevState[2]) % MOD;

            currState[5] = prevState[3];

            currState[6] = prevState[4];

            prevState = currState;
        }

        long res = 0;
        for(long v : prevState) {
            res = (res + v) % MOD;
        }

        return (int)res;

    }
}