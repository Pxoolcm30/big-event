package com.pxoolcm.mapper;

import com.pxoolcm.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/9 8:59
 */
@Mapper
public interface CategoryMapper {
    //新增文章
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time) " +
            "values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    //查询所有文章
    @Select("select * from category where create_user = #{id}")
    List<Category> list(Integer id);
    //根据id查询
    @Select("select * from category where id = #{id}")
    Category findById(Integer id);
    //根据id修改文章分类
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);
    //删除文章分类
    @Delete("delete from category where id = #{id}")
    void delete(Integer id);
}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm.mapper
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-09  08:59
 * @Description: TODO
 * @Version: 1.0
 */
