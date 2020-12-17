<h1 align="center">Rest leave management in a company</h1>

<h2>Application description :</h2>

* In achieving this project I used **Spring Boot** with Hibernate and Thymeleaf. 

* This project is created without JavaScript

<h3>Functionalities:</h3>

- [ ] An employee is part of one or more teams, each team being led by a boss (in turn a boss can has several subordinate teams and can has a boss, less a CEO - who manages his leave alone) . CEO - Chief Executive Officer

- [ ] The employee can take a rest leave at least 5 days before the chosen period and with a maximum of half a year before the chosen period. Number of vacantion days performed are calculated without taking into account days off  from calendar (Saturday, Sunday, public holidays) and are decreased from the total number of days that the employee benefits (initially established).

- [ ] In order for it to be approved, it must receive the approval of all direct bosses and respect the number of days left

- [ ] Direct superiors receive a leave request form, which they can reject or approve. The application is available for approval no later than two days before the holiday period, otherwise it is considered rejected.
