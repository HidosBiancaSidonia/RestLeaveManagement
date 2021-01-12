package restleavemanagement.dto;

import restleavemanagement.model.Person;

import java.util.Date;

public class LeaveRequestDto {
    private Date startDate;
    private Date endDate;
    private int nrStatus;
    private String status;
    private Person person;

    public LeaveRequestDto() {
    }

    public LeaveRequestDto(Date startDate, Date endDate, int nrStatus, String status, Person person) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.nrStatus = nrStatus;
        this.status = status;
        this.person = person;
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

    public int getNrStatus() {
        return nrStatus;
    }

    public void setNrStatus(int nrStatus) {
        this.nrStatus = nrStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "LeaveRequestDto{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", nrStatus=" + nrStatus +
                ", status='" + status + '\'' +
                ", person=" + person +
                '}';
    }
}
