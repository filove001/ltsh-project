package com.ltsh.common.util;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Random on 2018/1/31.
 */

public class ClassUtils {
    public static void main(String[] args) {
        loadClassByPackage();
    }
    public static List<Class<?>> loadClassByPackage() {
        return getClassList("com.ltsh.common.util.bean");
    }
    public static List<Class<?>> getClassList(String pkgName) {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            // 按文件的形式去查找
            String strFile = pkgName.replaceAll("\\.", "/");
            Enumeration<URL> urls = loader.getResources(strFile);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    String pkgPath = url.getPath();
                    if(protocol.equals("file")) {
                        String[] list = new File(pkgPath).list();
                        for (String str : list) {
                            if(str.endsWith(".class")) {
                                String className = pkgName + "." + str.substring(0, str.lastIndexOf(".class"));

//                                System.out.println(className);
                                Class<?> aClass = Class.forName(className);
                                classList.add(aClass);
                            }

                        }
                    }

                }
            }
        } catch (IOException e) {
            LogUtils.error(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            LogUtils.error(e.getMessage(), e);
        }

        return classList;
    }
}
