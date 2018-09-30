package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivanov", "Ivan", "Ivanovich",
              "Ivan_01", "Moscow Red squre 1 h1", "+79153925555", "Test1"), true);
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().deleteContact();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);

  }
}
