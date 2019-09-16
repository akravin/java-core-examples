package com.akravin.phonedirectory.utils;

import java.util.Objects;

public class PersonInfo {

    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String areaCode;
    private final String phone;

    public PersonInfo(String firstName, String middleName, String lastName, String areaCode, String phone) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.areaCode = areaCode;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonInfo that = (PersonInfo) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(areaCode, that.areaCode) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, areaCode, phone);
    }

    @Override
    public String toString() {
        return "firstName:"+firstName+", middleName:"+middleName+", lastName:"+lastName+
                ", areaCode:"+areaCode+", phone:"+phone;
    }
}
