package com.example.lianxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lianxi.XiangqingActivity;
import com.example.lianxi.bean.DataShopBean;
import com.example.lianxi.bean.ShopBean;
import com.example.lianxi.utils.StringUtils;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    private Context context;
    private List<DataShopBean.DataBean> list;

    public BannerAdapter(Context context, List<DataShopBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(StringUtils.Http2http(split[0])).into(img);
        container.addView(img);
        return img;



    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
