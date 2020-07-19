public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int p = pattern.length();
        int i = input.length();
        if (p > i) {
            return -1;
        }
        StringBuilder strBuilder = new StringBuilder();
        for (int j = 0; j < p; j += 1) {
            strBuilder.append(input.charAt(j));
        }
        RollingString rollInput = new RollingString(strBuilder.toString(), p);
        RollingString rollPattern = new RollingString(pattern, p);
        for (int j = 0; j < i - p; j++) {
            if (rollInput.hashCode() == rollPattern.hashCode()) {
                if (rollInput.equals(rollPattern)) {
                    return j;
                }
            }
            rollInput.addChar(input.charAt(j + p));

        }
        return -1;
    }

}
