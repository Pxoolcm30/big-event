package com.pxoolcm.mapper;

import com.pxoolcm.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/7 11:00
 */
@Mapper
public interface UserMapper {

    //通过用户名查找用户
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    //注册用户
    @Insert("insert into user(username,password,create_time,update_time)" +
            " values(#{username},#{password},now(),now())")
    void add(String username, String password);
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id};")
    void update(User user);
    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String ,Integer id);
}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm.mapper
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-07  11:00
 * @Description: TODO
 * @Version: 1.0
 */
