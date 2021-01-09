package com.jiang.advances.peace.entity;

import com.jiang.advances.peace.entity.imp.Service;

/**
 * Company: waiqin365
 * Description:
 * author:jjw
 * create 2021-01-08 16:17
 */
public class ServiceTwo implements Service {
    @Override
    public void doService() {
        System.out.println("this is service two");
    }

    @Override
    public String serviceType() {
        return "two";
    }
}