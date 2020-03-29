package priv.wenhao.base.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@TableName("school_admin")
@Data
public class SchoolAdminDto {
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;
	@TableField("account")
	private String account;
	@TableField("password")
	private String password;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("create_time")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("modify_time")
	private Date modifyTime;
	@TableField("is_deleted")
	private Integer deleted;
	@TableField("name")
	private String name;
}
