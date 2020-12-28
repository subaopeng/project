package com.evan.wj.dao;

import com.evan.wj.entity.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminMenuDAO extends JpaRepository<AdminMenu,Integer> {

    @Query(value = "select * from admin_menu where id in (select mid from admin_role_menu where rid in(select rid from admin_user_role where uid = ?1))",nativeQuery = true)
    public List<AdminMenu> findAllById(int userId);
    @Query(value = "select * from admin_menu where parent_id = ?1",nativeQuery = true)
    public List<AdminMenu> findById(int id);
}
