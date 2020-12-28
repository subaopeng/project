package com.evan.wj.service.Impl;


import com.evan.wj.dao.AdminUserRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service("adminUserRoleService")
public class AdminUserRoleServiceImpl {
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    public List<Integer> findRidByUidIs(int uid){
        return adminUserRoleDAO.findRidByUidIs(uid);
    }
}
