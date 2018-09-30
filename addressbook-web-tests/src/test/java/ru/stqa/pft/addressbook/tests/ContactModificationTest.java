package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoContactPage();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Ivanov", "Ivan", "Ivanovich",
              "Ivan_01", "Moscow Red squre 1 h1", "+79153925555", "Test1"), true);
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactData(new ContactData("Petrov", "Petr", "Ivanovich",
            "Ivan_01", "Moscow Red squre 1 h1", "+79153925555", null), false);
    app.getContactHelper().submitContactChange();
    app.getContactHelper().returnToContactCreation();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
