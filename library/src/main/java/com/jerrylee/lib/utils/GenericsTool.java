package com.jerrylee.lib.utils;

import android.support.annotation.NonNull;

import java.lang.reflect.ParameterizedType;

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 14:59
 */
public class GenericsTool {

    public static <T> T newInstance(Object object, int i) {
        if(object!=null){
            try {
                Class clazz = getInstance(object,i);
                T o = (T) clazz.newInstance();//o 即为泛型实例化对象
                return o;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

        }
        return null;

    }

    public static <T> T getInstance(Object object, int i) {
        if (object != null) {
            ParameterizedType pType = (ParameterizedType) object.getClass().getGenericSuperclass();
            Class clazz = (Class<T>) pType.getActualTypeArguments()[i];
            return (T) clazz;
        }
        return null;

    }

}
