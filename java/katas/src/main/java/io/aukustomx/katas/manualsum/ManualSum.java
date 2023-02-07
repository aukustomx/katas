package io.aukustomx.katas.manualsum;

import java.util.Arrays;

public class ManualSum {

    public static String add(String a, String b) {

        char[] longestArray;
        char[] smallestArray;

        //Let's generate two arrays of the same length to facilitate the processing
        if (a.length() == b.length()) {
            longestArray = a.toCharArray();
            smallestArray = b.toCharArray();
        } else {
            longestArray = a.length() > b.length() ? a.toCharArray() : b.toCharArray();
            var smallest = a.length() < b.length() ? a.toCharArray() : b.toCharArray();
            var diff = longestArray.length - smallest.length;
            smallestArray = addLeadingZeroes(new String(smallest), diff).toCharArray();
        }

        //Let's prepare a result array, filled of 0 char, that maybe need an
        // extra char to store a last add greater than 10.
        var result = new char[longestArray.length + 1];
        Arrays.fill(result, '0');

        var remain = 0;
        //Let's traverse both of the arrays. At this moment, they are of the same length.
        for (int i = longestArray.length - 1; i >= 0; i--) {

            var numberOfLongest = Character.getNumericValue(longestArray[i]);
            var numberOfSmallest = Character.getNumericValue(smallestArray[i]);
            var sum = numberOfLongest + numberOfSmallest + remain;

            if (sum >= 10) {
                result[i + 1] = Character.forDigit((sum - 10), 10);
                remain = 1;
            } else {
                result[i + 1] = Character.forDigit(sum, 10);
                remain = 0;
            }
        }

        //Maybe the last addition was greater than 10, so let's add the number 1
        // at the beginning of our resulting array.
        if (remain > 0) {
            result[0] = Character.forDigit(1, 10);
        }

        return removeLeadingZeroes(new String(result));
    }

    /*
    In case of the two inputs are of different length, we "normalize" them
     with the same length, adding leading zeroes.
     */
    private static String addLeadingZeroes(String input, int leadingZeroes) {
        if (leadingZeroes == 0) {
            return input;
        }
        var sb = new StringBuilder(input);
        for (int i = 0; i < leadingZeroes; i++) {
            sb.insert(0, 0);
        }

        return sb.toString();
    }

    /*
    In case of the extra character of the result were not necessary, we remove
    it from the result.
     */
    private static String removeLeadingZeroes(String input) {

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (character != '0') {
                return input.substring(i);
            }
        }
        return "";
    }
}
