/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int optimalIPL = 0;
        int sum = 0;
        int i = 0;
        int j = 0;
        while (sum < N) {
            sum += Math.pow(2, i);
            i+=1;
        }
        i -= 1;
        sum -= Math.pow(2, i);
        while (j <= i-1) {
            optimalIPL += (Math.pow(2, j) * j);
            j+=1;
        }
        optimalIPL += (i * (N + 1 - Math.pow(2, i)));
        return optimalIPL;
    }

    public static void main(String[] args) {
        System.out.println(optimalAverageDepth(5));
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        double optimalIPL = optimalIPL(N);
        return optimalIPL / N;
    }

    public static void randomInsert(BST<Integer> bst) {
        int random = RandomGenerator.getRandomInt(10000);
        while (true) {
            if (!bst.contains(random)) {
                bst.add(random);
                break;}
            else {random = RandomGenerator.getRandomInt(10000);}
        }
    }
}
