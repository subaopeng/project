package com.evan.wj.service.Impl;

import com.evan.wj.dao.CateGoryDao;
import com.evan.wj.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("cateGoryService")
public class CateGoryServiceImpl {
    @Autowired
    CateGoryDao cateGoryDao;

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return cateGoryDao.findAll(sort);
    }

    public Category get(int id) {
        Category c= cateGoryDao.findById(id).orElse(null);
        return c;
    }
}
