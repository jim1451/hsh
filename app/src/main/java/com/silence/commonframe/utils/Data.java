package com.silence.commonframe.utils;

import com.silence.commonframe.model.IsChoose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {
    private static  String name = "";
    private  static String token;




  private static  List<HashMap<String, String>> listmap;



    private static ArrayList<String> listId; //场所的ID



    private static  String photo;//注册电话



    private static String loginId; //注册id推送使用



    private static String cb_photo = "0";//场所通知电话  cb_message



    private static String cb_message  = "0";  //场所短信提示


    public static int getTestId() {
        return testId;
    }

    public static void setTestId(int testId) {
        Data.testId = testId;
    }

    private static  int testId ;//全局Id 在设备中做判断


    public static String getLoginId() {
        return loginId;
    }

    public static void setLoginId(String loginId) {
        Data.loginId = loginId;
    }

    public static String getCb_message() {
        return cb_message;
    }

    public static void setCb_message(String cb_message) {
        Data.cb_message = cb_message;
    }

    public static String getCb_photo() {
        return cb_photo;
    }

    public static void setCb_photo(String cb_photo) {
        Data.cb_photo = cb_photo;
    }

    public static String getLinkmanname() {
        return linkmanname;
    }

    public static void setLinkmanname(String linkmanname) {
        Data.linkmanname = linkmanname;
    }

    private static  String linkmanname;//联系人从首页面传递的值

    public static ArrayList<String> getListId() {
        return listId;
    }

    public static void setListId(ArrayList<String> listId) {
        Data.listId = listId;
    }

    public static ArrayList<IsChoose> getArrayList() {
        return arrayList;
    }

    public static void setArrayList(ArrayList<IsChoose> arrayList) {
        Data.arrayList = arrayList;
    }

    private static    ArrayList<IsChoose> arrayList;   //场所删除选择

    public static ArrayList<String> getListSiteid() {
        return listSiteid;
    }

    public static void setListSiteid(ArrayList<String> listSiteid) {
        Data.listSiteid = listSiteid;
    }

    private static  ArrayList<String>  listSiteid;    //删除场所的id


    public static List<String> getList() {
        return list;
    }

    public static void setList(List<String> list) {
        Data.list = list;
    }

    private static   List<String> list;//注册的场所的位置  listlistlocation

    public static List<String> getListlocation() {
        return listlocation;
    }

    public static void setListlocation(List<String> listlocation) {
        Data.listlocation = listlocation;
    }

    private static List<String>  listlocation;  //区域地址

    public static List<String> getListregionalism() {
        return listregionalism;
    }

    public static void setListregionalism(List<String> listregionalism) {
        Data.listregionalism = listregionalism;
    }

    private static List<String>  listregionalism;



    public static List<String> getListid() {
        return listid;
    }

    public static void setListid(List<String> listid) {
        Data.listid = listid;
    }

    private static  List<String>  listid;//注册场所设备的id

    public static List<String> getListIdKnowid() {
        return listIdKnowid;
    }

    public static void setListIdKnowid(List<String> listIdKnowid) {
        Data.listIdKnowid = listIdKnowid;
    }

    private static List<String> listIdKnowid;  //我知道的


    private static  String userid;//注册的useid




    public static String getUserid() {
        return userid;
    }

    public static void setUserid(String userid) {
        Data.userid = userid;
    }




    public static String getPhoto() {
        return photo;
    }

    public static void setPhoto(String photo) {
        Data.photo = photo;
    }



    public static List<HashMap<String, String>> getListmap() {
        return listmap;
    }

    public static void setListmap(List<HashMap<String, String>> listmap) {
        Data.listmap = listmap;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Data.token = token;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Data.name = name;
    }
}
