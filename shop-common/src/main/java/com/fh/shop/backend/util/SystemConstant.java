package com.fh.shop.backend.util;

import java.util.HashMap;
import java.util.Map;

public final class SystemConstant {

    public static final int STATUS_SUCCESS = 1;

    public static final int STATUS_ERROR = 0;

    public static final int EXECUTE_TIME_ERROR = -1;

    public static final String IMAGE_UPLOAD_PATH = "/images/";

    public static final String IMGCODE = "imgcode";

    public static final String SALT = "SAFWR8734LKJ-*&(*(*&&^";

    public static final int USER_LOCK_STATUS = 1;

    public static final int LOCK_COUNT = 3;

    public static final String CURR_USER = "user";

    public static final String LOGIN_URL = "/login.jsp";

    public static final String WHITE_LIST_URLS = "login.jhtml,/api/product/list.jhtml";

    public static final String FIELD_MAPPING = "{insertTimeStr:insertTime, updateTimeStr:updateTime, productPrice:price}";

    public static final String ORDER_COLUMN = "order[0][column]";

    public static final String ORDER_DIR = "order[0][dir]";

    public static final String COS_APPKEY = "AKIDa7WowubUhuXHvPa7ZO8jCXjmgVEnmRWW";
    public static final String COS_APPSECRET = "782mNzc7cxDWlqlV4mMl28qQpIg9f6b8";
    public static final String COS_AREA = "ap-beijing";
    public static final String COS_BUCKET_NAME = "fh1808-1257067485";
    public static final String COS_URL = "https://fh1808-1257067485.cos.ap-beijing.myqcloud.com/";
//    public static final Map FIELD_MAPPING = new HashMap();
//
//    // 静态块 加载类的时候就会执行
//    static {
//        FIELD_MAPPING.put("insertTimeStr", "insertTime");
//        FIELD_MAPPING.put("updateTimeStr", "updateTime");
//        FIELD_MAPPING.put("productPrice", "price");
//    }



}
