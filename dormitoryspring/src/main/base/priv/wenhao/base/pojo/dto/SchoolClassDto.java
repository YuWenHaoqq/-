package priv.wenhao.base.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("school_class")
public class SchoolClassDto {
	@ApiModelProperty("教室id")
	@TableId(value = "class_id",type =  IdType.AUTO)
	private Integer classId;
	@ApiModelProperty("教室名字")
	@TableField(value = "class_name")
	private String className;
	@ApiModelProperty("毕业年份")
	@TableField("class_year_system")
	private String overYear;
	@ApiModelProperty()
	@TableField("class_create")
	private Date createTime;
	@ApiModelProperty()
	@TableField("class_modify")
	private Date modifyTime;
	@ApiModelProperty("是否删除")
	@TableField("is_deleted")
	private Integer deleted;
}
