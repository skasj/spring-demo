package com.example.springdemo.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("user")
public class UserPo {
    @TableId(type=IdType.AUTO)
    Integer id;
    @TableField("userName")
    String userName;
    String password;

    public UserPo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
