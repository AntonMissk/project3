package org.example.domain;

public class users {
    private Integer person_id;
    private String first_name;
    private String last_name;
    private String email;
    private String city;
    private String street;



    public users(Integer id, String first_name, String last_name, String email, String city, String street){
        this.person_id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.city = city;
        this.street = street;

    }

    public users() {

    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public users (Integer person_id, String first_name, String last_name, String email){
        this.person_id = person_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public Integer getId() {
        return person_id;
    }

    public String getName() {
        return first_name;
    }

    public String getSurname() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return "Id" + person_id + "name" + first_name + "suranme" + last_name +
                "email" + email;
    }
}
