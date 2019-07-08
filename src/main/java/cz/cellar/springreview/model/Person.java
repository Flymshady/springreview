package cz.cellar.springreview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name= "person")
public class Person {

    @NotBlank
    @Column(name = "username")
    private String username;
    @NotBlank
    @Column(name = "password")
    private String password;
    @NotBlank
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;

    public Person(){}

    public Person(Person person){
        this.roles=person.getRoles();
        this.name=person.getName();
        this.username=person.getUsername();
        this.password=person.getPassword();
        this.id=person.getId();
    }

    public Person(@JsonProperty(value = "username") String username, @JsonProperty(value = "password") String password, @JsonProperty(value = "name") String name, @JsonProperty(value = "role") Role roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.roles= (Set<Role>) roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRolse(Set<Role> roles) {
        this.roles = roles;
    }
}
