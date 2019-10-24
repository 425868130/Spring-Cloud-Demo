package com.example.authcenter.dao.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.define.ServiceException;
import com.example.common.define.StatusCode;
import com.example.common.util.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.StringTypeHandler;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class User implements Serializable {

    private static final long serialVersionUID = 5382537995670726913L;
    private Long id;
    private String username;
    private String password;
    private String salt;
    /**
     * 这里JacksonTypeHandler的原因是mapper自动的反序列化会丢失类型,从而将Long列表转为Integer列表,
     * 如果再尝试将该user对象序列化化时将得到：java.lang.Integer cannot be cast to java.lang.Long
     * 而使用StringTypeHandler则将查询结果列作为String处理在调用set方法时手动序列化从而得到正确的类型
     */
    @TableField(typeHandler = StringTypeHandler.class)
    private List<Long> roleIds;

    public String getCredentialsSalt() {
        return username + salt + salt;
    }

    public User(User user) {
        Assert.notNull(user, ServiceException.withCode(StatusCode.PARAM_BLANK));
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.salt = user.salt;
        this.roleIds = user.roleIds;
    }

    /**
     * 当mapper注入查询结果时手动将字段json转为对象
     */
    public void setRoleIds(String roleIds) {
        this.roleIds = JSON.parseList(roleIds, Long.class);
    }

    /**
     * 由于默认set方法名称被占用，因此单独提供一个set方法
     * 此处不重载的原因是：mapper在注入查询结果时使用反射调用，
     * 传参为Object导致无法确定正确的set方法而抛出异常：java.lang.IllegalArgumentException: argument type mismatch
     *
     * @param roleIds
     */
    public void setRoleIdList(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
