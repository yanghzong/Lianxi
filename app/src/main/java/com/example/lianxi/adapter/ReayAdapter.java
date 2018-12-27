package com.example.lianxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lianxi.R;
import com.example.lianxi.bean.ShopBean;
import com.example.lianxi.utils.StringUtils;

import java.util.List;



public class ReayAdapter extends RecyclerView.Adapter<ReayAdapter.ViewHolder>{
    private Context context;
    private List<ShopBean.DataBean> list;
    private OnItemClick onItemClick;

    public ReayAdapter(Context context, List<ShopBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ReayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  itemView=View.inflate(context, R.layout.rv_item,null);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReayAdapter.ViewHolder viewHolder, final int i) {
        final ShopBean.DataBean dataBean = list.get(i);
        String[] split = dataBean.getImages().split("\\|");
        Glide.with(context).load(StringUtils.Http2http(split[0])).into(viewHolder.imgShop);
        viewHolder.tvTitle.setText(dataBean.getTitle());
        viewHolder.tvPrice.setText("￥:"+dataBean.getPrice());

        //通过接口回调进行传值
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClick.onItemClick(dataBean.getPid()+"");
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgShop;
        private final TextView tvTitle;
        private final TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgShop = itemView.findViewById(R.id.sdv_view);
            tvTitle = itemView.findViewById(R.id.text_title);
            tvPrice = itemView.findViewById(R.id.text_price);
        }
    }
    //1.定义接口
    public interface OnItemClick{
        void onItemClick(String pid);
    }

    //定义成员变量
    private OnItemClick mOnItemClick;

    //设置方法暴露给外界
    public void setOnItemClickListener(OnItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }
}
