package com.evan.wj.dao;

import com.evan.wj.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CateGoryDao extends JpaRepository<Category,Integer> {

}
