package org.ltsh.chat.service.entity;



import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Collection;

/**
 *  sys_user 用户信息 
 * @author fjz
 */
@Data
@ToString
public class UserInfo extends BaseEntity {
	public static final String tableName="user_info";
	public static final String tableRemarks="用户信息";

	/**
	 * 登录名
	 */
	private String loginName;//登录名
	/**
	 * 密码
	 */
	private String password;//密码
	/**
	 * 昵称
	 */
	private String nickName;
	private String name;//显示名
	private String tel;//电话号码
	private String phone;//手机号码
	private String address;//地址
	private String email;//电子邮件
	private String idcard;//身份证
	private String zip;//邮政编码
	private String status;//状态
	private Integer sex;//性别

	private java.util.Date birth;//出生日期 
	private String remarks;//备注

	private java.util.Date lastLoginTime;//最后登录时间 
	private Integer loginCount;//登录次数


}
