package com.evan.wj.service.Impl;


import com.evan.wj.dao.AdminRoleMenuDAO;
import com.evan.wj.dao.AdminUserRoleDAO;
import com.evan.wj.entity.AdminRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminRoleMenuService")
public class AdminRoleMenuServiceImpl {
    @Autowired
    AdminRoleMenuDAO adminRoleMenuDAO;

    public List<Integer> findMidByRid(int rid){
        return adminRoleMenuDAO.findMidByRid(rid);
    }
}
