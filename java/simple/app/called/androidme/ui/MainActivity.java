package simple.app.called.androidme.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import simple.app.called.androidme.R;

public class MainActivity extends AppCompatActivity
{
    private static long back_pressed = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed()
    {
        if(back_pressed+2000>System.currentTimeMillis())
            super.onBackPressed();
        back_pressed = System.currentTimeMillis();
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG).show();
    }
}
