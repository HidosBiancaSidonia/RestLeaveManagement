package restleavemanagement.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "leave_request")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status")
    private String status;

    @Column(name = "nr_status")
    private int nrStatus;

    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id")
    private Person person;

    public LeaveRequest() {
    }

    public LeaveRequest(Long id, Date startDate, Date endDate, String status, int nrStatus, Person person) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.nrStatus = nrStatus;
        this.person = person;
    }

    public LeaveRequest(Date startDate, Date endDate, String status, int nrStatus, Person person) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.nrStatus = nrStatus;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNrStatus() {
        return nrStatus;
    }

    public void setNrStatus(int nrStatus) {
        this.nrStatus = nrStatus;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
