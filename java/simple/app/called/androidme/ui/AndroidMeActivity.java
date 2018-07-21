package simple.app.called.androidme.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import simple.app.called.androidme.R;
import simple.app.called.androidme.data.AndroidImageAssets;

public class AndroidMeActivity extends AppCompatActivity
{
    private static long back_pressed = 0;
    private BodyPartFragment headFragment, bodyFragment, legFragment;
    private Toast exitToast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_me_activity);
        exitToast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG);

        if(savedInstanceState == null)
        {
            //Fragments
            headFragment = new BodyPartFragment();
            bodyFragment = new BodyPartFragment();
            legFragment = new BodyPartFragment();
            headFragment.setimageArray(AndroidImageAssets.getHeads());
            headFragment.setImageNum(2);
            bodyFragment.setimageArray(AndroidImageAssets.getBodies());
            bodyFragment.setImageNum(2);
            legFragment.setimageArray(AndroidImageAssets.getLegs());
            legFragment.setImageNum(2);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .add(R.id.body_container, bodyFragment)
                    .add(R.id.leg_container, legFragment).commit();
        }
    }

    @Override
    public void onBackPressed()
    {
        if(back_pressed+2000>System.currentTimeMillis())
        {
            super.onBackPressed();
            exitToast.cancel();
        }
        back_pressed = System.currentTimeMillis();
        exitToast.show();
    }
}
