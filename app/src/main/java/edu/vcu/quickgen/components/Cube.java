package edu.vcu.quickgen.components;

import edu.vcu.quickgen.interfaces.Scrambler;

/**
 * Created by minim_000 on 11/16/2015.
 */
public abstract class Cube {
    public abstract void scramble(String scramble);
    public abstract boolean isEquivalentTo(String scramble);
}
