package besuhr.guesturegame;

import android.content.Intent;

import java.util.Random;

public class PuzzleEasy {
    public static final int NUMBER_PARTS = 5;
    String[] parts;
    Random random = new Random();

    public PuzzleEasy(){
        parts = new String[NUMBER_PARTS];
        parts[0] = "1";
        parts[1] = "27";
        parts[2] = "39";
        parts[3] = "43";
        parts[4] = "68";

    }

    public boolean solved( String [] solution ) {
        if( solution != null && solution.length == parts.length ) {
            for( int i = 0; i < parts.length; i++ ) {
                if( !solution[i].equals( parts[i] ) )
                    return false;
            }
            return true;
        }
        else
            return false;
    }

    public String [] scramble( ) {
        String [] scrambled = new String[parts.length];
        for( int i = 0; i < scrambled.length; i++ )
            scrambled[i] = parts[i];

        while( solved( scrambled ) ) {
            for( int i = 0; i < scrambled.length; i++ ) {
                int n = random.nextInt( scrambled.length - i ) + i;
                String temp = scrambled[i];
                scrambled[i] = scrambled[n];
                scrambled[n] = temp;
            }
        }
        return scrambled;
    }

    public int getNumberOfParts() {return parts.length;}
}
