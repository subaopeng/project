package com.evan.wj.dao;

import com.evan.wj.entity.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole,Integer> {

    @Query(value = "select rid from admin_user_role where uid = ?1",nativeQuery = true)
    public List<Integer> findRidByUidIs(int uid);
}
