package com.example.lianxi.mvp;

import com.example.lianxi.bean.DataShopBean;
import com.example.lianxi.bean.ShopBean;
import com.example.lianxi.inter.ICallBack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class DataPresenter {
    private Model shopModel;
    private DataView dataView;


    public  void  attach(DataView dataView){
        this.dataView=dataView;
        shopModel=new Model();
    }


    public  void  DataShop(String  url){
        Type type=new TypeToken<DataShopBean>(){}.getType();
        shopModel.getShop(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                dataView.getData((DataShopBean) obj);
            }

            @Override
            public void onFailed(Exception e) {
            }


        },type);
    }

    public  void  detach(){
        if (shopModel!=null){
            shopModel=null;
        }
        if (dataView!=null){
            dataView=null;
        }
    }
}
