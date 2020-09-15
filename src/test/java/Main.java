import java.util.Stack;

public class Main {

    public static void main(String[] args) {
//        first assign
        int R = 4;
        int C = 3;
        char matrix[][] =
                {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}, {'a', 'b', 'c'}};

        spiralPrint(R, C, matrix);


//        second assignment
        String expr = "([)])";
        System.out.println(isExpressionBalanced(expr));


    }

    //    second assigment
    // function to check if expression are balanced
    static boolean isExpressionBalanced(String expr) {
        Stack<Character> st = new Stack<Character>();

        for (char chr : expr.toCharArray()) {
            switch (chr) {

                case '{':
                case '(':
                case '[':
                    st.push(chr);
                    break;

                case ']':
                    if (st.isEmpty() || st.pop() != '[')
                        return false;
                    break;
                case ')':
                    if (st.isEmpty() || st.pop() != '(')
                        return false;
                    break;
                case '}':
                    if (st.isEmpty() || st.pop() != '{')
                        return false;
                    break;
            }
        }
        return st.isEmpty();
    }

    //first assigment
    private static void spiralPrint(int m, int n, char[][] a) {
        int i, k = 0, l = 0;
        /*  k - starting row index
        m - ending row index
        l - starting column index
        n - ending column index
        i - iterator
        */

        while (k < m && l < n) {
            // Print the first row from the remaining rows
            for (i = l; i < n; ++i) {
                System.out.print(a[k][i] + " ");
            }
            k++;

            // Print the last column from the remaining columns
            for (i = k; i < m; ++i) {
                System.out.print(a[i][n - 1] + " ");
            }
            n--;

            // Print the last row from the remaining rows */
            if (k < m) {
                for (i = n - 1; i >= l; --i) {
                    System.out.print(a[m - 1][i] + " ");
                }
                m--;
            }

            // Print the first column from the remaining columns */
            if (l < n) {
                for (i = m - 1; i >= k; --i) {
                    System.out.print(a[i][l] + " ");
                }
                l++;
            }
        }
    }
}
