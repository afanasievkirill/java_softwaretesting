package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class AddContactToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().ContactPage();
      app.contact().initContactCreation();
      app.contact().create(new ContactData()
              .withFirstname("Ivan").withLastname("Ivanov").withAddress("Kremlin h1 ").withMobilePhone("+79153913435"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test_1"));
    }
  }

  @Test
  public void addToGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    Groups allGroups = app.db().groups();
    GroupData addedGroup = allGroups.iterator().next();
    if (allGroups.equals(contact.getGroups())) {
      app.goTo().ContactPage();
      app.contact().removeFromGroup(contact, addedGroup);
    }
    allGroups.removeAll(contact.getGroups());
    app.goTo().ContactPage();
    app.contact().addToGroup(contact, addedGroup);
    app.db().refresh(contact);
    assertThat(contact.getGroups(), hasItem(addedGroup));
  }
}
