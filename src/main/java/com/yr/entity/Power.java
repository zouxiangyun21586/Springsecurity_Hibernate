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
 * 资源
 */
@Entity
@Table(name = "securityPower")
public class Power {
	private Long id;
	private String resourceName; // 资源名称
	private String httpUrl; // 资源路径
	private Set<Role> roleList = new HashSet<>();

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="powername")
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	// 中间表表名,中间表中的角色id字段名,中间表中的用户id字段名
	@JoinTable(name = "security_pr", joinColumns = @JoinColumn(name = "power_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", resourceName=" + resourceName + ", httpUrl=" + httpUrl + "]";
	}
}
