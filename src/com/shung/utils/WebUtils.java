package com.shung.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Description: web層中使用的通用工具
 * @author: Eker
 * @date: 2022/12/27 上午 10:16
 * @version: V1.0
 */
public class WebUtils {

    /**
     * @titile: copyParameterToBean
     * @description: 將請求的參數一次性的注入javabean中的通用方法
     * @param value 要注入javabean中的Map類型參數
     * @param bean 注入的javabean對象
     * @return: 注入後的bean對象
     * @author: Eker
     * @date: 2022/12/27 上午 10:31
     */
    public static <T> T copyParameterToBean (Map value , T bean){
        //把請求的參數都注入bean中
        try {
            BeanUtils.populate(bean , value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    public static int parseInt(String str, int defaultValue){
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            //拋出會有錯誤訊息
//            throw new RuntimeException(e);
        }
        return defaultValue;
    }
}
