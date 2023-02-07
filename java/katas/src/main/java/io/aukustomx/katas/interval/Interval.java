package io.aukustomx.katas.interval;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Interval {

    public static int sumIntervals(int[][] intervals) {

        //A value to know if a combination happens each iteration of intervals
        var combinations = 10;

        //For more convenience manipulation I created a class called IntervalObject
        var intervalObjects = Arrays.stream(intervals)
                .map(IntervalObject::of)
                .collect(Collectors.toSet());

        //Process the resulting collection of intervals while there are more
        // intervals to combine. When no combination occurs, or when the size
        // of the resulting collection is 1, it doesn't make sense to traverse
        // the collection again.
        while (combinations > 0 && intervalObjects.size() > 1) {

            //Let's traverse the collection.
            var results = combineIntervals(intervalObjects);

            //Obtaining the processed collection.
            intervalObjects = (Set) results[0];

            //Retrieving how many combinations occurred in this iteration
            combinations = (int) results[1];
        }

        //Finally, we return the sum of intervals' lengths
        return intervalObjects.stream()
                .mapToInt(IntervalObject::length)
                .sum();
    }

    /**
     * I need both, the resulting intervals combined, if applies, and the number
     * of combinations that just happened in the process.
     *
     * @param intervals Combined intervals if applies.
     * @return The number of combinations that have been occurred.
     */
    private static Object[] combineIntervals(Set<IntervalObject> intervals) {
        var evaluatedIntervals = new HashSet<IntervalObject>();
        final int[] combinations = {0};

        for (var interval : intervals) {

            if (interval.length() == 0) {
                continue;
            }

            interval.getCombinableWith(evaluatedIntervals)
                    .ifPresentOrElse(combinableInterval -> {
                        var combinedInterval = IntervalObject.combine(interval, combinableInterval);
                        evaluatedIntervals.remove(combinableInterval);
                        evaluatedIntervals.add(combinedInterval);
                        combinations[0] = combinations[0] + 1;
                    }, () -> evaluatedIntervals.add(interval));

        }

        return new Object[]{evaluatedIntervals, combinations[0]};
    }
}
