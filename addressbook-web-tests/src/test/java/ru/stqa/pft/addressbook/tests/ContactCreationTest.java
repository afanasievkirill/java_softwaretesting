package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase{



  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/cumis.png");
    ContactData contact = new ContactData().
            withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withAddress("Moscow Red squre 1 h1").
            withHomephone("+79153925555").withNickname("Ivan_01").withGroup("Test1").withPhoto(photo);
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testBadContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().
            withFirstname("Ivan'").withMiddlename("Ivanovich").withLastname("Ivanov").withAddress("Moscow Red squre 1 h1").
            withHomephone("+79153925555").withNickname("Ivan_01").withGroup("Test1");
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
  }


}
