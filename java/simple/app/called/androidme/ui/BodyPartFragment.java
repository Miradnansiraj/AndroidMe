package simple.app.called.androidme.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import simple.app.called.androidme.R;
import simple.app.called.androidme.data.AndroidImageAssets;

public class BodyPartFragment extends Fragment
{
    private ImageView rootImage;
    private View rootView;
    private int imageNum = 0;

    public BodyPartFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        rootImage = rootView.findViewById(R.id.body_part_image_view);
        return rootView;
    }
}
