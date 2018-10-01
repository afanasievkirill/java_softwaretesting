package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String address;
  private final String homephone;
  private String group;

  public ContactData(String firstname, String middlename, String lastname, String nickname, String address, String homephone, String group) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.homephone = homephone;
    this.group = group;
  }

  public ContactData(String firstname) {
    this.firstname = firstname;
    this.middlename = null;
    this.lastname = null;
    this.nickname = null;
    this.address = null;
    this.homephone = null;
    this.group = null;
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

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) { //метод сравнения для обектов ContactData
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(middlename, that.middlename) &&
            Objects.equals(lastname, that.lastname);
  }


  @Override
  public int hashCode() {
    return Objects.hash(firstname, middlename, lastname);
  }

}
