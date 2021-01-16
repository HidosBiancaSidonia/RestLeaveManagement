package restleavemanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "buttons")
public class ForButtons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private long employee_id;
    private long boss_id;
    private boolean press;

    public ForButtons() {
    }

    public ForButtons(long id, long employee_id, long boss_id, boolean press) {
        this.id = id;
        this.employee_id = employee_id;
        this.boss_id = boss_id;
        this.press = press;
    }

    public ForButtons(long employee_id, long boss_id, boolean press) {
        this.employee_id = employee_id;
        this.boss_id = boss_id;
        this.press = press;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public long getBoss_id() {
        return boss_id;
    }

    public void setBoss_id(long boss_id) {
        this.boss_id = boss_id;
    }

    public boolean isPress() {
        return press;
    }

    public void setPress(boolean press) {
        this.press = press;
    }
}
