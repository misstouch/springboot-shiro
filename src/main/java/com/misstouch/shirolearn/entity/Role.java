package com.misstouch.shirolearn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hechao
 * @date create in 10:23 2018/5/8/008
 */
@Entity
@Table(name = "t_role")
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rolename;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private List<Permission> permissions;

    @ManyToMany
    @JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_id") })
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Transient
    public List<String> getPermissionsName() {
        List<String> list = new ArrayList<String>();
        List<Permission> perlist = getPermissions();
        for (Permission per : perlist) {
            list.add(per.getPermissionname());
        }
        return list;
    }

}
