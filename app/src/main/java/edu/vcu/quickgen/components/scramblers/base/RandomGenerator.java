package edu.vcu.quickgen.components.scramblers.base;

import edu.vcu.quickgen.interfaces.Scrambler;

public abstract class RandomGenerator implements Scrambler {


    protected final String[] MOVESET = new String[]{"R","L","F","B","U","D"};
    protected final String[] MODIFIERS = new String[]{"", "'","2"};

    public String getScramble(){
        return MOVESET[0]+MODIFIERS[0];
    }
    public abstract String getName();

    public String toString(){
        return getName();
    }

    @Override
    public String getScramble(int length) {
        StringBuilder scramble = new StringBuilder();
        int last = -1;
        int current;
        for(int i = 0;i < length;i++){
            while((current = (int)(Math.random()*MOVESET.length))==last);
            scramble.append(MOVESET[current]).append(MODIFIERS[(int)(Math.random()*MODIFIERS.length)]).append(" ");
            last = current;
        }

        //TODO: Fully implement modifiers
        return scramble.toString();
    }
}