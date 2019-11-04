package com.example.authcenter.dao.sysRole;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class SysRole implements Serializable {
    private static final long serialVersionUID = 261489156171952966L;
    private Long id;
    private String role;
    @TableField(typeHandler = StringTypeHandler.class)
    private List<Long> permissionIds;

    public SysRole(SysRole sysRole) {
        Assert.notNull(sysRole, "参数不能为空");
        this.id = sysRole.id;
        this.role = sysRole.role;
        this.permissionIds = sysRole.permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = JSON.parseList(permissionIds, Long.class);
    }

    public void setPermissionIdList(List<Long> permissionIdList) {
        this.permissionIds = permissionIdList;
    }
}
