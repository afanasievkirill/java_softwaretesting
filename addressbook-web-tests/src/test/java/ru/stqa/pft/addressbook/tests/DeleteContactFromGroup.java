package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

public class DeleteContactFromGroup extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().ContactPage();
      app.contact().initContactCreation();
      app.contact().create(new ContactData()
              .withFirstname("Ivan").withLastname("Petrov").withAddress("Kremlin").withMobilePhone("+7915391353333"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Удаление"));
    }
  }

  @Test
  public void removeContactFromGroupTests() {

    ContactData contact = app.db().contacts().iterator().next();
    Groups allGroups = app.db().groups();
    GroupData deleteGroup = allGroups.iterator().next();
    if (!deleteGroup.equals(contact.getGroups())) {
      app.goTo().ContactPage();
      app.contact().addToGroup(contact, deleteGroup);
    }
    allGroups.removeAll(contact.getGroups());
    app.goTo().ContactPage();
    app.contact().removeFromGroup(contact, deleteGroup);
    app.db().refresh(contact);
    assertThat(contact.getGroups(), not(hasItem(deleteGroup)));

  }
}
