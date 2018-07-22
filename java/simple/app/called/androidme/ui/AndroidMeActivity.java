package simple.app.called.androidme.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import simple.app.called.androidme.R;
import simple.app.called.androidme.data.AndroidImageAssets;

public class AndroidMeActivity extends AppCompatActivity
{
    private BodyPartFragment headFragment, bodyFragment, legFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_me_activity);
        if(savedInstanceState == null)
        {
            //Using the bundle
            int headIndex = 0, bodyIndex = 0, legIndex =0;
            headIndex = getIntent().getExtras().getInt("Head");
            bodyIndex = getIntent().getExtras().getInt("Body");
            legIndex = getIntent().getExtras().getInt("Leg");

            //Fragments
            headFragment = new BodyPartFragment();
            bodyFragment = new BodyPartFragment();
            legFragment = new BodyPartFragment();
            headFragment.setimageArray(AndroidImageAssets.getHeads());
            headFragment.setImageNum(headIndex);
            bodyFragment.setimageArray(AndroidImageAssets.getBodies());
            bodyFragment.setImageNum(bodyIndex);
            legFragment.setimageArray(AndroidImageAssets.getLegs());
            legFragment.setImageNum(legIndex);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .add(R.id.body_container, bodyFragment)
                    .add(R.id.leg_container, legFragment).commit();
        }
    }
}
