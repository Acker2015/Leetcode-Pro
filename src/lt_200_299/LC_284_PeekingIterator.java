package lt_200_299;


import java.util.Iterator;
/**
 * @lc app=leetcode id=284 lang=java
 *
 * [284] Peeking Iterator
 */
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
public class LC_284_PeekingIterator implements Iterator<Integer>{
    private boolean hasPeek;
    private Integer peekVal;
    private Iterator<Integer> iterator;

    public LC_284_PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        this.hasPeek = false;

    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!hasPeek) {
            hasPeek = true;
            peekVal = iterator.next();
        }
        return peekVal;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (hasPeek) {
            hasPeek = !hasPeek;
            return peekVal;
        } else {
            return iterator.next();
        }
    }

    @Override
    public boolean hasNext() {
        return hasPeek || iterator.hasNext();
    }
}
