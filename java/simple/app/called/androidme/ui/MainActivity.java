package simple.app.called.androidme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import simple.app.called.androidme.R;
import simple.app.called.androidme.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener
{
    private static long back_pressed = 0;
    private Toast exitToast;
    private Intent androidMeActivity;
    private Button nextButton;
    private int headIndex = 0, bodyIndex = 0, legIndex = 0;
    private Bundle bundle;
    private boolean twoPane = false;
    private BodyPartFragment headFragment, bodyFragment, legFragment;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exitToast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG);
        androidMeActivity = new Intent(this, AndroidMeActivity.class);
        bundle = new Bundle();

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(androidMeActivity);
            }
        });

        if(findViewById(R.id.android_me_linear) != null)
        {
            twoPane = true;
            nextButton.setVisibility(View.GONE);


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

                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .add(R.id.body_container, bodyFragment)
                        .add(R.id.leg_container, legFragment).commit();
            }
        }

    }

    @Override
    public void onImageSelected(int position)
    {

        if(twoPane)
        {
            BodyPartFragment newFragment = new BodyPartFragment();

            if(position<=11)
            {
                newFragment.setimageArray(AndroidImageAssets.getHeads());
                newFragment.setImageNum(position);
                fragmentManager.beginTransaction().replace(R.id.head_container, newFragment).commit();
            }
            else if(position<=23)
            {
                newFragment.setimageArray(AndroidImageAssets.getBodies());
                newFragment.setImageNum(position-12);
                fragmentManager.beginTransaction().replace(R.id.body_container, newFragment).commit();

            }
            else
            {
                newFragment.setimageArray(AndroidImageAssets.getLegs());
                newFragment.setImageNum(position-24);
                fragmentManager.beginTransaction().replace(R.id.leg_container, newFragment).commit();
            }
        }
        else
        {
            Toast.makeText(this, selectionText(position), Toast.LENGTH_LONG).show();

            if(position<=11)
                headIndex = position;
            else if(position<=23)
                bodyIndex = position-12;
            else
                legIndex = position-24;

            bundle.putInt("Head", headIndex);
            bundle.putInt("Body", bodyIndex);
            bundle.putInt("Leg", legIndex);
            androidMeActivity.putExtras(bundle);
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

    private String selectionText(int position)
    {
        if(position<=11)
            return ("Selected head: " + (position+1));
        else if(position<=23)
            return ("Selected Body: " + (position-11));
        return ("Selected Leg: " + (position-23));
    }
}
