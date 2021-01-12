package restleavemanagement.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name =  "person", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "vacation_days")
    private int vacationDays;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "person_role",
            joinColumns =
                    { @JoinColumn(name = "person_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private Collection<Role> role;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_team",
            joinColumns = @JoinColumn(
                    name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "team_id", referencedColumnName = "id"))

    private Set<TeamType> teamTypes;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn

    private LeaveRequest leaveRequests;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_boss",
            joinColumns = @JoinColumn(
                    name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "boss_id", referencedColumnName = "id"))

    private Set<Person> boss;

    public Person() {
    }

    public Person(Long id, String name, String email, String password, String phoneNumber, int vacationDays, Collection<Role> role, Set<TeamType> teamTypes, LeaveRequest leaveRequests, Set<Person> boss) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.vacationDays = vacationDays;
        this.role = role;
        this.teamTypes = teamTypes;
        this.leaveRequests = leaveRequests;
        this.boss = boss;
    }

    public Person(String name, String email, String password, String phoneNumber, int vacationDays, Collection<Role> role, Set<TeamType> teamTypes, LeaveRequest leaveRequests, Set<Person> boss) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.vacationDays = vacationDays;
        this.role = role;
        this.teamTypes = teamTypes;
        this.leaveRequests = leaveRequests;
        this.boss = boss;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public Collection<Role> getRole() {
        return role;
    }

    public void setRole(Collection<Role> role) {
        this.role = role;
    }

    public Set<TeamType> getTeamTypes() {
        return teamTypes;
    }

    public void setTeamTypes(Set<TeamType> teamTypes) {
        this.teamTypes = teamTypes;
    }

    public LeaveRequest getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(LeaveRequest leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    public Set<Person> getBoss() {
        return boss;
    }

    public void setBoss(Set<Person> boss) {
        this.boss = boss;
    }

}
