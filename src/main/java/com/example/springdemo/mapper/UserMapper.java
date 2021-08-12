package com.example.springdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo.model.po.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<UserPo> {

    @Select("select id,nickName,password from 3dker_user where nickName = #{name}")
    UserPo selectByName(@Param("name") String name);
}
