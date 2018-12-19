package model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 4, max = 10)
    private String email;

    @NotNull
    @Size(min = 6, max = 16)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<Indication> indicationList = new HashSet<Indication>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"))
    private Set<UserRole> roleUser = new HashSet<UserRole>();


    public User() {
    }


    public enum UserRole{
        ROLE_USER,
        ROLE_ADMIN
    }

    public User(String login, String password) {
        this.email = login;
        this.password = password;



    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Indication> getIndicationList() {
        return indicationList;
    }

    public void setIndicationList(Set<Indication> indicationList) {
        this.indicationList = indicationList;
    }

    public Set<UserRole> getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(Set<UserRole> roleUser) {
        this.roleUser = roleUser;
    }
}