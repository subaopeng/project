package com.evan.wj.service.Impl;


import com.evan.wj.dao.AdminMenuDAO;
import com.evan.wj.entity.AdminMenu;
import com.evan.wj.entity.AdminRoleMenu;
import com.evan.wj.entity.AdminUserRole;
import com.evan.wj.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service("adminMenuService")
public class AdminMenuServiceImpl {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    AdminUserRoleServiceImpl adminUserRoleService;
    @Autowired
    AdminRoleMenuServiceImpl adminRoleMenuService;
    @Autowired
    AdminMenuDAO adminMenuDAO;
    public List<AdminMenu> getMenusByCurrentUser() {
        // 从数据库中获取当前用户
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByUsername(username);
        // 获得当前用户对应的所有角色的 id 列表
//        List<Integer> rids = adminUserRoleService.findRidByUidIs(user.getId());
//                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());

        // 查询出这些角色对应的所有菜单项
//        List<Integer> menuIds = adminRoleMenuService.findMidByRid(rids.get(0));
//                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(user.getId()).stream().distinct().collect(Collectors.toList());

        // 处理菜单项的结构
        handleMenus(menus);
        return menus;
    }

    public void handleMenus(List<AdminMenu> menus) {
        for (AdminMenu menu : menus) {
            List<AdminMenu> children = getAllByParentId(menu.getId());
            menu.setChildren(children);
        }

        Iterator<AdminMenu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            AdminMenu menu = iterator.next();
            if (menu.getParentId() != 0) {
                iterator.remove();
            }
        }
    }

    public List<AdminMenu> getAllByParentId(int id){
       return adminMenuDAO.findById(id);
    }
}
