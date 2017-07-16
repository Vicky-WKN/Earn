package com.earn.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.earn.R;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

/**
 * Created by asus on 2017/7/15.
 */

public class TestNomalAdapter extends StaticPagerAdapter {

    private int[] imgs = {
            R.drawable.test_image,
            R.drawable.test_image2
    };

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }
}
