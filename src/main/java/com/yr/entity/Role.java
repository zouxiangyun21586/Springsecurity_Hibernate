package com.yr.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 角色
 */
@Entity
@Table(name = "securityRole")
public class Role {
	private int id;
	private String name;
	private String roleCode;// 角色代码
	private Set<Power> powerList = new HashSet<>();
	private Set<User> userList = new HashSet<>();

	// 删除权限时,如果有角色在使用就不能进行删除.如果没角色使用可以进行删除
	// 删除角色时,如果有用户在使用就不能进行删除.如果没用户使用可以进行删除
	// 删除用户一定可以删除
	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="rolename")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="rolecode")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@ManyToMany(mappedBy = "roleList", fetch = FetchType.LAZY) // 放弃维护关系,转交给权限中的 roleList 属性来维护
	public Set<Power> getPowerList() {
		return powerList;
	}

	public void setPowerList(Set<Power> powerList) {
		this.powerList = powerList;
	}

	 @ManyToMany(fetch = FetchType.LAZY)
	 // 中间表表名,中间表中的角色id字段名,中间表中的用户id字段名
	 @JoinTable(name = "security_ur", joinColumns = @JoinColumn(name = "role_id") , inverseJoinColumns = @JoinColumn(name = "user_id") )
	public Set<User> getUserList() {
		return userList;
	}

	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", roleCode=" + roleCode + ", powerList=" + powerList
				+ ", userList=" + userList + "]";
	}

}
