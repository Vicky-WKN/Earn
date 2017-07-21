package com.earn.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.earn.Contract.NewsContract;
import com.earn.R;
import com.earn.model.News;
import com.earn.model.OnStringListener;
import com.earn.model.StringModelImpl;
import com.earn.util.Api;
import com.earn.view.adapter.RecyclerViewAdapter;
import com.earn.view.adapter.TestNomalAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.IconHintView;

import java.util.ArrayList;

/**
 * Created by asus on 2017/7/15.
 */

public class NewFragment extends Fragment implements NewsContract.View {
    View view;

    View viewPager;
    RollPagerView rollPagerView;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerViewAdapter mAdapter;
    SwipeRefreshLayout refreshLayout ;
    NewsContract.Presenter newsPresenter;

    private StringModelImpl model;

    private ArrayList<News.result> list = new ArrayList<>();
    private Gson gson = new Gson();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_new,container,false);
        viewPager = inflater.inflate(R.layout.news_viewpager_item,container,false);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);//设置下拉更新旋转颜色
        initViewPager();

        //newsPresenter = new NewsPresenter(getContext(),this);
        //newsPresenter.start();
        //ArrayList<News.result> list = new ArrayList<>();
        showResult(list);
        model = new StringModelImpl(getContext());

        //---------------------------------------------------

        model.load(Api.GUOKR_ARTICLES, new OnStringListener() {
            @Override
            public void onSuccess(String result) {

                // 由于果壳并没有按照日期加载的api
                // 所以不存在加载指定日期内容的操作，当要请求数据时一定是在进行刷新
                list.clear();

                Log.d("打印json内容", result);
                try {

                    News question = gson.fromJson(result, News.class);

                    for (News.result re : question.getResult()){

                        list.add(re);

                    }
                    showResult(list);

                } catch (JsonSyntaxException e) {
                    showError();
                }

                stopLoading();

            }

            @Override
            public void onError(VolleyError error) {
                stopLoading();
                showError();
            }
        });
        /*showLoading();
        OkHttpUtil okHttpUtil = OkHttpUtil.getOkHttpUtil();
        okHttpUtil.get(Api.GUOKR_ARTICLES, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("获取results","失败",e);
                stopLoading();
                showError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();

                News question = gson.fromJson(responseBody,News.class);
                for(News.result re : question.getResult()){
                    list.add(re);
                }
                //ArrayList<News.result> list1 = new ArrayList<>();
                showResult(list);
                //final String token = JsonUtil.getToken(responseBody);
                Log.d("打印json内容", responseBody);

            }
        });
        stopLoading();*/
        //--------------------------------------------------




        //list.add(new News.result());
//        News n = new News();
//        News.result re = n.result;
//        showResult(list);
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
        Snackbar.make(refreshLayout,"加载失败",Snackbar.LENGTH_INDEFINITE)
                .setAction("重试", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //presenter.refresh();
                    }
                }).show();
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
    public void showResult(ArrayList<News.result> list) {
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

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        if (presenter != null){
            this.newsPresenter =  presenter;
        }
    }
}
