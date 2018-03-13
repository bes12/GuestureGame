package besuhr.guesturegame;

import java.util.Random;

public class PuzzleHard {
    public static final int NUMBER_PARTS = 15;
    String[] parts;
    Random random = new Random();

    public PuzzleHard(){
        parts = new String[NUMBER_PARTS];
        parts[0] = "5";
        parts[1] = "13";
        parts[2] = "29";
        parts[3] = "38";
        parts[4] = "49";
        parts[5] = "55";
        parts[6] = "63";
        parts[7] = "74";
        parts[8] = "81";
        parts[9] = "99";
        parts[10] = "100";
        parts[11] = "109";
        parts[12] = "155";
        parts[13] = "200";
        parts[14] = "255";

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
