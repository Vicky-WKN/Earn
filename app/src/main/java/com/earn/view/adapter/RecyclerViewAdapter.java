package com.earn.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.earn.R;

import java.util.ArrayList;

/**
 * Created by asus on 2017/7/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //判断是否是recyclerView头
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private ArrayList<String> mDatas = new ArrayList<>();
    private View mHeaderView ;//头部件
    private OnItemClickListener mListener;


    public void setOnItemClickListener(OnItemClickListener li){
        mListener = li;
    }

    /**
     * 设置recycler头
     * @param headerView
     */
    public void setHeaderView(View headerView){
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView(){
        return mHeaderView;
    }

    /**
     * 设置数据
     * @param datas
     */
    public void addDatas(ArrayList<String> datas){
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 获取类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position){
        if(mHeaderView == null) return TYPE_NORMAL;
        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    /**
     * 获取Holder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mHeaderView != null&&viewType == TYPE_HEADER) return  new Holder(mHeaderView);
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item_layout,parent,false);
        return new Holder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,int position)
    {
        if(getItemViewType(position) == TYPE_HEADER) return ;
        final int pos = getRealPosition(viewHolder);
        final String data = mDatas.get(pos);
        if(viewHolder instanceof Holder){
            ((Holder) viewHolder).text.setText(data);
            if((mListener == null)) return;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mListener.onItemClick(pos,data);
                }
            });
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder){
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position :position-1;
    }

    @Override
    public int getItemCount(){
        return mHeaderView == null?mDatas.size():mDatas.size()+1;
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView text;
        public Holder(View itemView){
            super(itemView);
            if(itemView == mHeaderView) return;
            text = (TextView) itemView.findViewById(R.id.textViewTitle);
        }
    }
    interface OnItemClickListener{
        void onItemClick(int position,String data);
    }

}
