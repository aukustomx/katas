package io.aukustomx.katas.interval;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

class IntervalObject {

    private final int min;
    private final int max;
    private final int length;

    private IntervalObject(int min, int max) {
        this.min = min;
        this.max = max;
        this.length = this.max - this.min;
    }

    public int length() {
        return this.length;
    }

    public static IntervalObject of(int[] intervalArray) {
        return new IntervalObject(intervalArray[0], intervalArray[1]);
    }

    public static IntervalObject of(int min, int max) {
        return new IntervalObject(min, max);
    }

    public static IntervalObject combine(IntervalObject first, IntervalObject second) {
        var min = Math.min(first.min, second.min);
        var max = Math.max(first.max, second.max);
        return IntervalObject.of(min, max);
    }

    public Optional<IntervalObject> getCombinableWith(Set<IntervalObject> otherIntervals) {
        return otherIntervals.stream()
                .filter(interval -> (this.min >= interval.min && this.min <= interval.max)
                        || (this.max >= interval.min && this.max <= interval.max)
                        || (this.min >= interval.min && this.max <= interval.max)
                        || (interval.min >= this.min && interval.max <= this.max))
                .findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntervalObject that = (IntervalObject) o;
        return min == that.min && max == that.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    public String toString() {
        return "[" + this.min + ", " + this.max + "]";
    }
}
