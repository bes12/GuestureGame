package besuhr.guesturegame;

import java.util.Random;

public class PuzzleMedium {
    public static final int Number_Parts = 10;
    String[] parts;
    Random random = new Random();

    public PuzzleMedium(){
        parts = new String[Number_Parts];
        parts[0] = "1";
        parts[1] = "27";
        parts[2] = "39";
        parts[3] = "43";
        parts[4] = "68";
        parts[5] = "79";
        parts[6] = "82";
        parts[7] = "93";
        parts[8] = "100";
        parts[9] = "155";
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
