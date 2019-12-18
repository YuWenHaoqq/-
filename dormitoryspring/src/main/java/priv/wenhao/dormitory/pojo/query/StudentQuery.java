package priv.wenhao.dormitory.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * Description: StudentQuery
 * Author: yuWenHao
 * Date: 2019/12/18
 */
@Data
public class StudentQuery {
	@ApiModelProperty(value = "学号",required = true)
	private String stuId;
	@ApiModelProperty(value = "关联班级表id",required = true)
	private Integer classId;
	@ApiModelProperty(value = "学生姓名",required = true)
	private String stuName;
	@ApiModelProperty(value = "学生性别",required = true)
	private int stuSex;
	@ApiModelProperty(value = "账号",required = true)
	private String stuAccount;
	@ApiModelProperty(value = "密码",required = true)
	private String stuPassword;
	@ApiModelProperty(value = "学生出生年月",required = true)
	private Date stuBirthday;
	@ApiModelProperty(value = "教师工号",required = true)
	private String teacherId;
	@ApiModelProperty(value = "寝室表id",required = true)
	private String dormId;
	@ApiModelProperty("创建时间")
	private Date stuCreate;
	@ApiModelProperty("修改时间")
	private Date stuModify;
	@ApiModelProperty("是否删除")
	private Integer deleted;
}
