package restleavemanagement.dto;

public class PersonRegistrationDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String vacationDays;

    public PersonRegistrationDto(){

    }

    public PersonRegistrationDto(String name, String email, String password, String phoneNumber, String vacationDays) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.vacationDays = vacationDays;
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

    public String getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(String vacationDays) {
        this.vacationDays = vacationDays;
    }
}
