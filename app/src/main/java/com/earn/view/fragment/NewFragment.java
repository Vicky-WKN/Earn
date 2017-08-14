package com.earn.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.earn.Contract.NewsContract;
import com.earn.R;
import com.earn.model.NewResult;
import com.earn.presenter.NewsPresenter;
import com.earn.view.adapter.RecyclerViewAdapter;
import com.earn.view.adapter.TestNomalAdapter;
import com.google.gson.Gson;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.IconHintView;

import java.util.ArrayList;

/**
 * Created by asus on 2017/7/15.
 */

public class NewFragment extends Fragment implements NewsContract.View,SwipeRefreshLayout.OnRefreshListener {
    View view;
    View viewPager;
    RollPagerView rollPagerView;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerViewAdapter mAdapter;
    SwipeRefreshLayout refreshLayout ;
    NewsContract.Presenter newsPresenter;

    private ArrayList<NewResult> list = new ArrayList<>();
    private Gson gson = new Gson();

    //新闻分类
    private int param = 1;

    public static  NewFragment newInstance(int param){
        NewFragment fragmentOne = new NewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("param", param);
        //fragment保存参数，传入一个Bundle对象
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //判断类型
        if(getArguments()!=null){
            //取出保存的值
            param = getArguments().getInt("param");
            //new ToastUtil(getActivity(),"param="+param);
        }

        // TODO 自动生成的方法存根
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        //return contentView = inflater.inflate(R.layout.fragment,
          //      container, false);

        view = inflater.inflate(R.layout.fragment_new,container,false);
        viewPager = inflater.inflate(R.layout.news_viewpager_item,container,false);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);//设置下拉更新旋转颜色
        initViewPager();

        newsPresenter = new NewsPresenter(getActivity(),this);
        //newsPresenter = new NewsPresenter(getContext(),this);
        newsPresenter.start(param);
        //ArrayList<News.result> list = new ArrayList<>();
        //showResult(list);
        refreshLayout.setOnRefreshListener(this);
        return view;
    }

    /**
     * 初始化viewPager的数据
     */
    private void initViewPager() {
        //图片浏览设置
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


        //        mAdapter.setOnItemClickListener(new RecyclerViewAdapter().setOnItemClickListener(); {
//            @Override
//            public void onItemClick(int position, String data) {
//                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     * 更改数据
     * @return
     */
    //    private ArrayList<String> generateData() {
//        ArrayList<String> datas = new ArrayList<>();
//        for(int i =0 ;i<20;i++){
//            datas.add("数据"+i);
//        }
//        return datas;
//    }
    private void setHeader(RecyclerView view)
    {
        //View header = LayoutInflater.from(getActivity()).inflate(viewPager,view,false);
        mAdapter.setHeaderView(viewPager);
    }





    //------------------------分割线（mvp)-------------------//

    @Override
    public void showError() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(mRecyclerView, "加载失败", Snackbar.LENGTH_INDEFINITE)
                        .setAction("重试", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                newsPresenter.refresh(param);
                                //presenter.refresh();
                            }
                        }).show();
            }
        });
    }

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showResult(final ArrayList<NewResult.News> list) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mAdapter == null) {
                    mAdapter = new RecyclerViewAdapter(getActivity());
                    /**
                     * 然后设置点击阅读
                     */
                    mRecyclerView.setAdapter(mAdapter);
                }
                mAdapter.addDatas(list);

                setHeader(mRecyclerView);
            }
        });

    }


    //--------------------------------------------更新新闻------------------------------------
    @Override
    public void onRefresh() {
        newsPresenter.refresh(param);
    }

//    @Override
//    public void onStart(){
//        newsPresenter.start(param);
//    }


}
