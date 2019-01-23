package com.yr.entity;

import java.beans.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 用户
 */
@Entity
@Table(name = "securityUser")
public class User implements Serializable{
	private Long id;
    private String name;// 姓名
    private String account;// 账号
    private String password;// 密码
    private Set<Role> roles = new HashSet<Role>();// 拥有的角色
    private String roleSet = "";

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="username")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(mappedBy = "userList", fetch = FetchType.LAZY) // 放弃维护关系,转交给角色中的 userList 属性来维护
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Transient // 不写入到数据库中
	public String getRoleSet() {
		for (Role role : roles) {
			roleSet = roleSet + role.getName() + ","; // 多数据的时候用 , 分隔
		}
		if(roleSet != null && !roleSet.equals("")){
			roleSet = roleSet.substring(0,(roleSet.length()-1)); // 不为空的时候 从0下标取值 ,将
		}
		return roleSet;
	}

	public void setRoleSet(String roleSet) {
		this.roleSet = roleSet;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", account=" + account + ", password=" + password + "]";
	}

}