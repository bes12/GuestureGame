package besuhr.guesturegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean startEasy(View v){
        Intent insertIntent = new Intent(this, Easy.class);
        this.startActivity(insertIntent);
        overridePendingTransition(R.anim.fade_in_and_scale, 0);
        return true;
    }

    public boolean startMedium(View v){
        Intent insertIntent = new Intent(this, Medium.class);
        this.startActivity(insertIntent);
        overridePendingTransition(R.anim.slide_from_left, 0);
        return true;
    }

    public boolean startHard(View v){
        Intent insertIntent = new Intent(this, Hard.class);
        this.startActivity(insertIntent);
        overridePendingTransition(R.anim.slide_from_right, 0);
        return true;
    }

}
