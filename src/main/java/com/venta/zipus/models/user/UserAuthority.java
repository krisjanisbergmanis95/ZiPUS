package com.venta.zipus.models.user;

import javax.persistence.*;
import java.util.Collection;

@Table(name = "UserAuthorityTable")
@Entity
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rId")
    private int rId;

    @Column(name = "RoleTitle")
    private String roleTitle;

    @ManyToMany(mappedBy = "authorities")
    private Collection<User> users;

    public UserAuthority() {
    }

    public UserAuthority(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserAuthority{" +
                "rId=" + rId +
                ", roleTitle='" + roleTitle + '\'' +
                '}';
    }
}
