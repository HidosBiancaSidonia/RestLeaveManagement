<h1 align="center">Rest leave management in a company</h1>

<h2>Application description :</h2>

* In achieving this project I used **Spring Boot** with Hibernate and Thymeleaf. 

* This project is created without JavaScript

<h3>Functionalities:</h3>

- [x] An employee is part of one or more teams, each team being led by a boss (in turn a boss can has several subordinate teams and can has a boss, less a CEO - who manages his leave alone) . CEO - Chief Executive Officer

- [X] The employee can take a rest leave at least 5 days before the chosen period and with a maximum of half a year before the chosen period. Number of vacantion days performed are calculated without taking into account days off  from calendar (Saturday, Sunday, public holidays) and are decreased from the total number of days that the employee benefits (initially established).

- [X] In order for it to be approved, it must receive the approval of all direct bosses and respect the number of days left

- [X] Direct superiors receive a leave request form, which they can reject or approve. The application is available for approval no later than two days before the holiday period, otherwise it is considered rejected.

<h3>LogIn Page :</h3>
<img width="960" alt="Screenshot_1" src="https://user-images.githubusercontent.com/58684695/104957645-352e4e80-59d7-11eb-8b95-9d9f37dc02d3.png">

<h3>What an employee sees on his page after logging in: </h3>
<img width="600" alt="Screenshot_2" src="https://user-images.githubusercontent.com/58684695/104958026-f64cc880-59d7-11eb-9338-d939b82209b5.png">
<img width="600" alt="Screenshot_3" src="https://user-images.githubusercontent.com/58684695/104958128-23997680-59d8-11eb-9855-e2456a2514d3.png">
<img width="600" alt="Screenshot_4" src="https://user-images.githubusercontent.com/58684695/104958130-25633a00-59d8-11eb-89c0-02065e6cc4e6.png">

<h3> What a boss sees on his page after logging in: </h3>

<img width="600" alt="Screenshot_5" src="https://user-images.githubusercontent.com/58684695/104958657-18931600-59d9-11eb-9b68-9e42ad045070.png">
<img width="600" alt="Screenshot_6" src="https://user-images.githubusercontent.com/58684695/104958659-1a5cd980-59d9-11eb-9f32-fba65215a207.png">
<img width="600" alt="Screenshot_7" src="https://user-images.githubusercontent.com/58684695/104958661-1b8e0680-59d9-11eb-918f-e15d4493dde8.png">

<h4> Database:</h4>
<img width="734" alt="Screenshot_8" src="https://user-images.githubusercontent.com/58684695/104961576-454a2c00-59df-11eb-83d2-0f21345cbd69.png">
