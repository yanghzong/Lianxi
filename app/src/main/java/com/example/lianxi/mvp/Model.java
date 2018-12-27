package com.example.lianxi.mvp;


import com.example.lianxi.inter.ICallBack;
import com.example.lianxi.utils.OkHttpUtil;

import java.lang.reflect.Type;


public class Model {


    public void getShop(String url, ICallBack callBack, Type type)
    {
        OkHttpUtil.getInstance().doget(url,callBack,type);

    }


}
