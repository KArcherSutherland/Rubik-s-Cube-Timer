package edu.vcu.quickgen.utils;

import java.util.LinkedList;


public class LimitedQueue<E> extends LinkedList<E> {
    private int sizeLimit;

    public LimitedQueue(int limit)
    {
        sizeLimit = limit;
    }

    @Override
    public boolean add(E o) {
        super.addFirst(o);
        while (size() > sizeLimit) {
            super.removeLast();
        }
        return true;
    }
}