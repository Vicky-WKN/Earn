package com.earn.view.fragment;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.earn.R;
import com.earn.util.Constants;
import com.earn.view.MatrixToImageWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by asus on 2017/7/15.
 */

public class InviteFragment extends Fragment{
    ListView listView;
    View view;
    ImageView erWeiMaIcon;
    ImageView peopleList;
    ImageView erWeiMa;
    View eLayout;
    View pLayout;
    private TextView studentMoney;
    static InviteFragment inviteFragment;
    public static InviteFragment getInstance()
    {
        if (inviteFragment == null)
        {
            inviteFragment = new InviteFragment();
            return inviteFragment;
        }else{
            return inviteFragment;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_invite,container,false);
        eLayout = (View) view.findViewById(R.id.layout_erweima);
        pLayout = (View) view.findViewById(R.id.layout_peopleList);
        erWeiMaIcon = (ImageView) view.findViewById(R.id.erweima_icon);
        peopleList = (ImageView) view.findViewById(R.id.people_list_icon);
        erWeiMaIcon.setColorFilter(Color.YELLOW);
        erWeiMaIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pLayout.setVisibility(View.GONE);
                eLayout.setVisibility(View.VISIBLE);
                erWeiMaIcon.setColorFilter(Color.YELLOW);
                peopleList.setColorFilter(Color.WHITE);
            }
        });
        peopleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eLayout.setVisibility(View.GONE);
                pLayout.setVisibility(View.VISIBLE);
                erWeiMaIcon.setColorFilter(Color.WHITE);
                peopleList.setColorFilter(Color.YELLOW);
            }
        });

        erWeiMa = (ImageView) view.findViewById(R.id.invite_erweima);
        Resources res=getResources();
        Bitmap bmp= BitmapFactory.decodeResource(res, R.mipmap.logo);
        Bitmap bitmap = null;
        bitmap = MatrixToImageWriter.generateBitmap("http://39.108.98.193:8080/EarnServer",200,200);
        Bitmap bitmap1 = MatrixToImageWriter.addLogo(bitmap,bmp);
        erWeiMa.setImageBitmap(bitmap1);
        studentMoney = (TextView) view.findViewById(R.id.invite_money);
        studentMoney.setText(String.valueOf(Constants.studentMoney));
        initListView();
        return view;
    }

    private void initListView() {
        listView = (ListView) view.findViewById(R.id.people_list);
        List<HashMap<String,String>> list = new ArrayList<>();
        for(int i =0;i<0;i++)
        {
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("name","张三");
            map.put("grade","1.21");
            map.put("time","2017.4.23");
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),
                list,
                R.layout.item_list_people,
                new String[]{"name","grade","time"},
                new int[]{R.id.item_people_name,R.id.item_people_grade,R.id.item_people_time});
        listView.setAdapter(adapter);
    }
}
