package com.evan.wj.dao;

import com.evan.wj.entity.AdminRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRoleMenuDAO extends JpaRepository<AdminRoleMenu,Integer> {

    @Query(value = "select mid from admin_role_menu where rid = ?1",nativeQuery = true)
    public List<Integer> findMidByRid(int rid);
}
