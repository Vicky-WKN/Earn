package com.earn.view.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.earn.R;
import com.earn.model.NewResult;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Iterator;

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
    private ArrayList<String> pics = new ArrayList<>();
    private ArrayList<String> uri = new ArrayList<>();

    private Context context;
    public RecyclerViewAdapter(Context context)
    {
        this.context = context;
    }

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
     * @param list
     */
    public void addDatas(ArrayList<NewResult.News> list){
        mDatas.clear();
        //News news = new News();
        Iterator it = list.iterator();
        while (it.hasNext()){
             NewResult.News re = (NewResult.News) it.next();
            mDatas.add(re.getTitle());
            pics.add(re.getImgLinks());
            //uri.add(re.getLink());
            //Log.d("标题是:",re.getTitle());
        }

//        for (int i = 0;i<20;i++)
//        {
////            News.result result =list.get(i);
////            mDatas.add(result.getTitle());
//            mDatas.add("数据"+i);
//        }
      // notify();
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
        final String pi = pics.get(pos);
        //final String u = uri.get(pos);
        if(viewHolder instanceof Holder){
            ((Holder) viewHolder).text.setText(data);
            //Log.d("图片是:",pi);
            if(pi!=null)
            {
                Uri imageUri = Uri.parse(pi);
                ((Holder) viewHolder).simpleDraweeView.setImageURI(imageUri);
            }

            //if((mListener == null)) return;
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v){
//                   // mListener.onItemClick(pos,data);
//                    Intent intent = new Intent();
//                    intent.setClass(context, WebActivity.class);
//                    intent.putExtra("uri",u);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                }
//            });
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
        SimpleDraweeView simpleDraweeView;
        //ImageView image;
        public Holder(View itemView){
            super(itemView);
            if(itemView == mHeaderView) return;
            text = (TextView) itemView.findViewById(R.id.textViewTitle);
            //image = (ImageView) itemView.findViewById(R.id.imageViewCover);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.imageViewCover);
        }
    }
    interface OnItemClickListener{
        void onItemClick(int position,String data);
    }

}