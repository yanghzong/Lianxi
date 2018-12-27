package com.example.lianxi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lianxi.adapter.ReayAdapter;
import com.example.lianxi.bean.ShopBean;
import com.example.lianxi.inter.ApiUri;
import com.example.lianxi.mvp.MyView;
import com.example.lianxi.mvp.Presenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyView {
    private RecyclerView rvShop;
    private Presenter presenter;
    private List<ShopBean.DataBean> shoplist;
    private ReayAdapter reayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏原有标题
        getSupportActionBar().hide();
        //查找控件
        rvShop= findViewById(R.id.recycler_view);
        shoplist = new ArrayList<>();
        presenter = new Presenter();
        presenter.attach(this);
        presenter.Shop(ApiUri.seek);

        reayAdapter = new ReayAdapter(this,shoplist);
        rvShop.setAdapter(reayAdapter);
        rvShop.setLayoutManager(new GridLayoutManager(MainActivity.this,
                2));
      reayAdapter.setOnItemClickListener(new ReayAdapter.OnItemClick() {
          @Override
          public void onItemClick(String pid) {
              Intent intent = new Intent(MainActivity.this,XiangqingActivity.class);
              intent.putExtra("pid",pid);
              startActivity(intent);
          }
      });


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }

    @Override
    public void getShop(ShopBean shopBean) {
        List<ShopBean.DataBean> data = shopBean.getData();
        if (data!=null){
             shoplist.clear();
             shoplist.addAll(data);
             reayAdapter.notifyDataSetChanged();
        }
    }

}

