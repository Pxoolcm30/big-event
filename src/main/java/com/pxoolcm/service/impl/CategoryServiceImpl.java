package com.pxoolcm.service.impl;

import com.pxoolcm.mapper.CategoryMapper;
import com.pxoolcm.pojo.Category;
import com.pxoolcm.service.CategoryService;
import com.pxoolcm.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/9 8:58
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    //新增文章
    @Override
    public void add(Category category) {
        //完善属性值
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        //在ThreadLocal中获取操作用户的id
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        //通过ThreadLocal获取该用户的id
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return categoryMapper.list(id);
    }

    @Override
    public Category findById(Integer id) {
        Category c = categoryMapper.findById(id);
        return c;
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm.service.impl
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-09  08:58
 * @Description: TODO
 * @Version: 1.0
 */
