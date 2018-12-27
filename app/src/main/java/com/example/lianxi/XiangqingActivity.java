package com.example.lianxi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lianxi.bean.DataShopBean;
import com.example.lianxi.inter.ApiUri;
import com.example.lianxi.mvp.DataPresenter;
import com.example.lianxi.mvp.DataView;
import com.example.lianxi.utils.StringUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class XiangqingActivity extends AppCompatActivity implements DataView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView  tvPrice;
    @BindView(R.id.sdv_img)
    ImageView  sdvImg;
    private Unbinder bind;
    private List<DataShopBean.DataBean> datashoplist;
    private DataPresenter presenter;
    private DataShopBean.DataBean data;
    private GenericDraweeHierarchyBuilder mBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        bind = ButterKnife.bind(this, this);

        //得到传过来的pid值  通过p层对象调用方法传值   在p层进行url地址拼接  使用model对象进行数据请求地址
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");//得到的值
        Toast.makeText(this,""+pid,Toast.LENGTH_LONG).show();//测试

        presenter = new DataPresenter();
        presenter.attach(this);
        presenter.DataShop(ApiUri.xiangqing+pid);











    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }



    @Override
    public void getData(DataShopBean dataShopBean) {
        data = dataShopBean.getData();
        if (data !=null){
            tvTitle.setText(data.getTitle());
            tvPrice.setText(""+ data.getPrice());
            String images = data.getImages();
            String[] split = images.split("\\|");
            Glide.with(this).load(StringUtils.Http2http(split[0])).into(sdvImg);

        }

    }

}
