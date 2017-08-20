package com.baway.duanxiaohong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.baway.duanxiaohong.R;
import com.baway.duanxiaohong.bean.ImageBean;
import com.bumptech.glide.Glide;

import java.util.List;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ImageBean.美女Bean> list;
    private Context mContext;
    private onItemClickListener listener;

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public MyAdapter(List<ImageBean.美女Bean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
//        options = new ImageOptions.Builder().build();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.itemClick(view, (Integer) view.getTag());
                }
            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        Glide.with(mContext).load(list.get(position).getImgsrc()).into(holder1.image);
        Animation animation = new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(6000);
        holder1.image.setAnimation(animation);

        holder1.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image1);
        }
    }
    public interface onItemClickListener{
        void itemClick(View view,int position);
    }


}
