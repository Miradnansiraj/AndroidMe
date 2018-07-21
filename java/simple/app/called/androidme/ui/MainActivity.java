package simple.app.called.androidme.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import simple.app.called.androidme.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageSelected(int position)
    {
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
    }
}
