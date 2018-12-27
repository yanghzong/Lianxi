package com.example.lianxi.mvp;

import com.example.lianxi.bean.ShopBean;
import com.example.lianxi.inter.ICallBack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Presenter {
    private Model shopModel;
    private MyView myView;


    public  void  attach(MyView myView){
        this.myView=myView;
        shopModel=new Model();
    }

    public  void  detach(){
        if (shopModel!=null){
            shopModel=null;
        }
        if (myView!=null){
            myView=null;
        }
    }

    public  void  Shop(String  url){
        Type type=new TypeToken<ShopBean>(){}.getType();
        shopModel.getShop(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                myView.getShop((ShopBean) obj);
            }

            @Override
            public void onFailed(Exception e) {
            }


        },type);
    }

}
