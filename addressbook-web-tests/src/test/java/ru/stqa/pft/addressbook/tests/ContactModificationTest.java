package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test 
  public void testContactModification() {
    app.goTo().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivanov", "Ivan", "Ivanovich",
              "Ivan_01", "Moscow Red squre 1 h1", "+79153925555", "Test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Petrov",
            "Petr", "Ivanovich", "Ivan_01", "Moscow Red squre 1 h1",
            "+79153925555", null);
    app.getContactHelper().fillContactData(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactList();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
