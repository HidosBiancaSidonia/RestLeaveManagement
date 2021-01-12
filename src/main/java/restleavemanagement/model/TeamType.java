package restleavemanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "team_type")
public class TeamType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
