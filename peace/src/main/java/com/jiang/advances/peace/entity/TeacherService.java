package com.jiang.advances.peace.entity;

import com.jiang.advances.peace.entity.imp.UserService;

/**
 * Company: waiqin365
 * Description:
 * author:jjw
 * create 2021-01-08 15:31
 */
public class TeacherService implements UserService {


    @Override
    public String getName() {
        return ("Teacher:");
    }

    @Override
    public String getSex() {
        return ("Teacher:");
    }
}
