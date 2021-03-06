package besuhr.guesturegame;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
//import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class Easy extends AppCompatActivity implements View.OnTouchListener{
    public static int STATUS_BAR_HEIGHT = 24; // in dp
    public static int ACTION_BAR_HEIGHT = 56; // in dp
    private PuzzleEasyView puzzleEasyView;
    private PuzzleEasy puzzleEasy;
    private int statusBarHeight;
    private int actionBarHeight;
    //private GestureDetector detector;

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        puzzleEasy = new PuzzleEasy( );

        Point size = new Point( );
        getWindowManager( ).getDefaultDisplay( ).getSize( size );
        int screenHeight = size.y;
        int puzzleWidth = size.x;

        Resources res = getResources();
        DisplayMetrics metrics = res.getDisplayMetrics( );
        float pixelDensity = metrics.density;

        TypedValue tv = new TypedValue( );
        actionBarHeight = ( int ) ( pixelDensity * ACTION_BAR_HEIGHT );
        if( getTheme( ).resolveAttribute( android.R.attr.actionBarSize,
                tv, true ) )
            actionBarHeight = TypedValue.complexToDimensionPixelSize( tv.data,
                    metrics );

        statusBarHeight = ( int ) ( pixelDensity * STATUS_BAR_HEIGHT );
        int resourceId =
                res.getIdentifier( "status_bar_height", "dimen", "android" );
        if( resourceId != 0 ) // found resource for status bar height
            statusBarHeight = res.getDimensionPixelSize( resourceId );

        int puzzleHeight = screenHeight - statusBarHeight - actionBarHeight;
        puzzleEasyView = new PuzzleEasyView( this, puzzleWidth, puzzleHeight,
                puzzleEasy.getNumberOfParts( ) );
        String [] scrambled = puzzleEasy.scramble( );
        puzzleEasyView.fillGui( scrambled );
        puzzleEasyView.enableListener( this );
        setContentView( puzzleEasyView );

        /*DoubleTapHandler dth = new DoubleTapHandler( );
        detector = new GestureDetector( this, dth );
        detector.setOnDoubleTapListener( dth );
        */
    }

    public boolean onTouch( View v, MotionEvent event ) {
        int index = puzzleEasyView.indexOfTextView( v );
        int action = event.getAction( );
        switch( action ) {
            case MotionEvent.ACTION_DOWN:
                // initialize data before move
                puzzleEasyView.updateStartPositions( index, ( int ) event.getY( ) );
                // bring v to front
                puzzleEasyView.bringChildToFront( v );
                break;
            case MotionEvent.ACTION_MOVE:
                // update y position of TextView being moved
                puzzleEasyView.moveTextViewVertically( index, ( int ) event.getY( ) );
                break;
            case MotionEvent.ACTION_UP:
                // move is complete: swap the 2 TextViews
                int newPosition = puzzleEasyView.tvPosition( index );
                puzzleEasyView.placeTextViewAtPosition( index, newPosition );
                // if user just won, disable listener to stop the game
                if( puzzleEasy.solved( puzzleEasyView.currentSolution( ) ) ) {
                    Intent winner = new Intent(this, Win.class);
                    startActivity(winner);
                }
                break;
        }
        return true;
    }



    /*public boolean onTouchEvent( MotionEvent event ) {
        detector.onTouchEvent( event );
        return true;
    }

    private class DoubleTapHandler
            extends GestureDetector.SimpleOnGestureListener {
        public boolean onDoubleTapEvent( MotionEvent event ) {
            int touchY = ( int ) event.getRawY( );
            // y coordinate of the touch within puzzleView is
            // touchY - actionBarHeight - statusBarHeight
            int index = puzzleEasyView.indexOfTextView( touchY
                    - actionBarHeight - statusBarHeight );
            if( puzzleEasyView.getTextViewText( index )
                    .equals( puzzleEasy.wordToChange( ) ) )
                puzzleEasyView.setTextViewText( index, puzzleEasy.replacementWord( ) );
            return  true;
        }
    }*/
}
