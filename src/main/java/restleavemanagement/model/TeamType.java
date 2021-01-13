package restleavemanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "team_type")
public class TeamType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public TeamType() {
    }

    public TeamType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TeamType(String name) {
        this.name = name;
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
}
