/*
You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.

A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).

The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.

Implement the MyCalendarTwo class:

MyCalendarTwo() Initializes the calendar object.
boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.


Example 1:

Input
["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
Output
[null, true, true, true, false, true, true]

Explanation
MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
myCalendarTwo.book(10, 20); // return True, The event can be booked.
myCalendarTwo.book(50, 60); // return True, The event can be booked.
myCalendarTwo.book(10, 40); // return True, The event can be double booked.
myCalendarTwo.book(5, 15);  // return False, The event ca not be booked, because it would result in a triple booking.
myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.


Constraints:

0 <= start < end <= 109
At most 1000 calls will be made to book.
 */

import java.util.Map;
import java.util.TreeMap;

class MyCalendarTwo {
    TreeMap<Integer, Integer> overlap;
    TreeMap<Integer, Integer> events;

    public MyCalendarTwo() {
        overlap = new TreeMap<>();
        events  = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> prev = overlap.floorEntry(start);
        if (prev != null && prev.getValue() > start) return false;
        Map.Entry<Integer, Integer> after = overlap.ceilingEntry(start);
        if (after != null && after.getKey() < end) return false;

        prev = events.floorEntry(start);


        if (prev != null && prev.getValue() > start) {
            overlap.put(start, Math.min(end, prev.getValue()));

            start = prev.getKey();
            end = Math.max(end, prev.getValue());
            events.remove(start);
        }

        after = events.ceilingEntry(start);

        while (after != null && after.getKey() < end) {
            overlap.put(after.getKey(), Math.min(end, after.getValue()));

            end = Math.max(end, after.getValue());
            events.remove(after.getKey());
            after = events.ceilingEntry(start);

        }

        events.put(start, end);

        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */