package com.junxin.freepaykotlin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.junxin.freepaykotlin.bean.UserInfoBean;
import com.junxin.freepaykotlin.iface.SPKeys;

import java.util.ArrayList;
import java.util.List;

public class SPUtil {

    /**
     * 保存在手机里面的文件名
     */
    private static SharedPreferences sp;

    public static void ini(Context context) {
        sp = context.getSharedPreferences("share_date", Context.MODE_PRIVATE);
    }
    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     * @param key
     * @param object
     */
    public static void setParam( String key, Object object){

        String type = object.getClass().getSimpleName();
        SharedPreferences.Editor editor = sp.edit();

        if("String".equals(type)){
            editor.putString(key, (String)object);
        }
        else if("Integer".equals(type)){
            editor.putInt(key, (Integer)object);
        }
        else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)object);
        }
        else if("Float".equals(type)){
            editor.putFloat(key, (Float)object);
        }
        else if("Long".equals(type)){
            editor.putLong(key, (Long)object);
        }
        editor.commit();
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getParam( String key, Object defaultObject){
        String type = defaultObject.getClass().getSimpleName();
        if("String".equals(type)){
            return sp.getString(key, (String)defaultObject);
        }

        else if("Integer".equals(type)){
            return sp.getInt(key, (Integer)defaultObject);
        }

        else if("Boolean".equals(type)){
            return sp.getBoolean(key, (Boolean)defaultObject);
        }

        else if("Float".equals(type)){
            return sp.getFloat(key, (Float)defaultObject);
        }

        else if("Long".equals(type)){
            return sp.getLong(key, (Long)defaultObject);
        }

        return null;
    }

    /**
     * 清除所有数据
     * @param context
     */
    public static void clearAll(Context context) {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }

    /**
     * 清除指定数据
     * @param context
     */
    public static void clearItem(Context context,String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 保存List
     * @param tag
     * @param datalist
     */
    public static <T> void setDataList(String tag, List<T> datalist) {
        SharedPreferences.Editor editor = sp.edit();
        if (null == datalist || datalist.size() <= 0)
            return;
        Lig.d("保存数据-->222 ");
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.putString(tag, strJson);
        editor.commit();

    }

    /**
     * 获取List
     * @param tag
     * @return
     */
    public static  <T> List<T> getDataList(String tag) {
        List<T> datalist=new ArrayList<T>();
        String strJson = sp.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;

    }


    public static void updateName(String name){
        String userInfo = (String) SPUtil.getParam(SPKeys.Companion.getUSER(), "");
        if (!TextUtils.isEmpty(userInfo)) {
            UserInfoBean userInfoBean = new Gson().fromJson(userInfo, UserInfoBean.class);
            userInfoBean.setNickname(name);
            SharedPreferences.Editor editor = sp.edit();
            String s = new Gson().toJson(userInfoBean);
            editor.putString(SPKeys.Companion.getUSER(), s);
            editor.commit();
        }
    }
    public static void updateAvatar(String avatar){
        String userInfo = (String) SPUtil.getParam(SPKeys.Companion.getUSER(), "");
        if (!TextUtils.isEmpty(userInfo)) {
            UserInfoBean userInfoBean = new Gson().fromJson(userInfo, UserInfoBean.class);
            userInfoBean.setAvatar(avatar);
            SharedPreferences.Editor editor = sp.edit();
            String s = new Gson().toJson(userInfoBean);
            editor.putString(SPKeys.Companion.getUSER(), s);
            editor.commit();
        }
    }
    public static void updateToken(String token){
        String userInfo = (String) SPUtil.getParam(SPKeys.Companion.getUSER(), "");
        if (!TextUtils.isEmpty(userInfo)) {
            UserInfoBean userInfoBean = new Gson().fromJson(userInfo, UserInfoBean.class);
            userInfoBean.setToken(token);
            SharedPreferences.Editor editor = sp.edit();
            String s = new Gson().toJson(userInfoBean);
            editor.putString(SPKeys.Companion.getUSER(), s);
            editor.commit();
        }
    }
}