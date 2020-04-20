package com.tedu.sso.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tedu.sso.pojo.User;

//引入MyBatisPlus实现单表的CRUD操作，无需写SQL语句，比较复杂需要自己写SQL
public interface UserMapper {
	/*
	 * 1、检查用户信息是否存在，根据paramType类型进行查询
	 *         注意：由于使用${..}所以参数里一定要借助@Param来进行映射
	 */
	@Select("select count(*) from tb_user where ${paramType} = #{param}")
	public Integer check(@Param("paramType") String paramType,
			@Param("param") String param);
	
	/*
	 * 2、添加用户信息	
	 */
	@Insert("insert into tb_user (username,password,phone,email,created,updated) "+
	 "values(#{userName},#{password},#{phone},#{email},#{createDate},#{createDate})")
	public void InsertUser(User user);
	
	/*
	 * 3、用户登录
	 */
	@Select("select count(*) from tb_user where username = #{username} and password = #{password}")
	public Integer Login(@Param("username") String username, @Param("password")String password);
	
	/*
	 * 通过用户名获取该用户的所有数据
	 */
	@Select("select * from tb_user where username = #{param}")
	public User Query(@Param("param") String param);
}