package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String home;
    private final String mobilePh;
    private final String workPh;
    private final String fax;
    private final String email;
    private final String yearOfBirth;
    private final String extraAddress;
    private final String extraPhone;
    private String group;

    public ContactData(String firstname, String middlename, String lastname, String nickname, String title,
                       String company, String address, String home, String mobilePh, String workPh, String fax,
                       String email, String yearOfBirth, String extraAddress, String extraPhone, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobilePh = mobilePh;
        this.workPh = workPh;
        this.fax = fax;
        this.email = email;
        this.yearOfBirth = yearOfBirth;
        this.extraAddress = extraAddress;
        this.extraPhone = extraPhone;
        this.group = group;
    }

    public ContactData(int id, String firstname, String middlename, String lastname, String nickname, String title,
                       String company, String address, String home, String mobilePh, String workPh, String fax,
                       String email, String yearOfBirth, String extraAddress, String extraPhone, String group) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobilePh = mobilePh;
        this.workPh = workPh;
        this.fax = fax;
        this.email = email;
        this.yearOfBirth = yearOfBirth;
        this.extraAddress = extraAddress;
        this.extraPhone = extraPhone;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobilePh() {
        return mobilePh;
    }

    public String getWorkPh() {
        return workPh;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public String getExtraAddress() {
        return extraAddress;
    }

    public String getExtraPhone() {
        return extraPhone;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
