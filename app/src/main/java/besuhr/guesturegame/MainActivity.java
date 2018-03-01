package besuhr.guesturegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public boolean beginGame(View v){
        Log.w("MainActivity","Add Selected");
        Intent insertIntent = new Intent(this, GamePlay.class);
        this.startActivity(insertIntent);
        overridePendingTransition(R.anim.fade_in_and_scale, 0);
        return true;
    }

    public void viewScore(View v){

    }
}
