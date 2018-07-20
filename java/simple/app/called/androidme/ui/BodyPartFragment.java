package simple.app.called.androidme.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import simple.app.called.androidme.R;
import simple.app.called.androidme.data.AndroidImageAssets;

public class BodyPartFragment extends Fragment
{
    private ImageView rootImage;
    private View rootView;
    private int imageNum;
    private List<Integer> imageArray;
    private static String IMAGENUM = "image_num", IMAGEARRAY = "image_array";

    public BodyPartFragment()
    {
        imageArray = AndroidImageAssets.getHeads();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //Check saveInstanceState
        if(savedInstanceState != null)
        {
            imageNum = savedInstanceState.getInt(IMAGENUM);
            imageArray = savedInstanceState.getIntegerArrayList(IMAGEARRAY);
        }

        rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        rootImage = rootView.findViewById(R.id.body_part_image_view);
        rootImage.setImageResource(imageArray.get(imageNum));

        rootImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                imageNum++;
                if(imageNum >= imageArray.size())
                    imageNum = 0;
                rootImage.setImageResource(imageArray.get(imageNum));
            }
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState)
    {
        currentState.putInt(IMAGENUM, imageNum);
        currentState.putIntegerArrayList(IMAGEARRAY,(ArrayList<Integer>) imageArray);
    }

    public void setImageNum(int imageNum)
    {
        this.imageNum = imageNum;
    }

    public void setimageArray(List<Integer> imageArray)
    {
        this.imageArray = imageArray;
    }
}
