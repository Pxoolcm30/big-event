package com.pxoolcm.service;

import com.pxoolcm.pojo.Category;

import java.util.List;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/9 8:57
 */
public interface CategoryService {
    //添加文章分类
    void add(Category category);
    //查询文章分类
    List<Category> list();
    //根据id查询分类
    Category findById(Integer id);

    void update(Category category);

    void delete(Integer id);
}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm.service
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-09  08:57
 * @Description: TODO
 * @Version: 1.0
 */
