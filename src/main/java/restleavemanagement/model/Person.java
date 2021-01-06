package restleavemanagement.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
@Table(name =  "person", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Person {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "vacation_days")
    private String vacationDays;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_role",
            joinColumns = @JoinColumn(
                    name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_team",
            joinColumns = @JoinColumn(
                    name = "id_person", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_team", referencedColumnName = "id"))

    private Set<TeamType> teamTypes;

    public Person() {

    }

    public Person(Long id, String name, String phoneNumber, String vacationDays, String email, String password, Collection<Role> roles, Set<TeamType> teamTypes) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vacationDays = vacationDays;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.teamTypes = teamTypes;
    }

    public Person(String name, String phoneNumber, String vacationDays, String email, String password, Collection<Role> roles, Set<TeamType> teamTypes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vacationDays = vacationDays;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.teamTypes = teamTypes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(String vacationDays) {
        this.vacationDays = vacationDays;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Set<TeamType> getTeamTypes() {
        return teamTypes;
    }

    public void setTeamTypes(Set<TeamType> teamTypes) {
        this.teamTypes = teamTypes;
    }
}
