package com.earn.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.earn.R;
import com.earn.view.adapter.RecyclerViewAdapter;
import com.earn.view.adapter.TestNomalAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.IconHintView;

import java.util.ArrayList;

/**
 * Created by asus on 2017/7/15.
 */

public class NewFragment extends Fragment  {
    View view;

    View viewPager;
    RollPagerView rollPagerView;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerViewAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_new,container,false);

        viewPager = inflater.inflate(R.layout.news_viewpager_item,container,false);
        rollPagerView = (RollPagerView) viewPager.findViewById(R.id.rollPagerView);
        rollPagerView.setHintView(new IconHintView(getActivity(),R.drawable.dark_dot,R.drawable.white_dot));
        //rollPagerView.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW,Color.WHITE));
        //rollPagerView.setHintView(new TextHintView(getActivity()));
        //rollPagerView.setHintView(null);//隐藏指示器
        rollPagerView.setAdapter(new TestNomalAdapter());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addDatas(generateData());
        setHeader(mRecyclerView);
//        mAdapter.setOnItemClickListener(new RecyclerViewAdapter().setOnItemClickListener(); {
//            @Override
//            public void onItemClick(int position, String data) {
//                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    private ArrayList<String> generateData() {
        ArrayList<String> datas = new ArrayList<>();
        for(int i =0 ;i<20;i++){
            datas.add("数据"+i);
        }
        return datas;
    }


    private void setHeader(RecyclerView view)
    {
        //View header = LayoutInflater.from(getActivity()).inflate(viewPager,view,false);
        mAdapter.setHeaderView(viewPager);
    }

}
