package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
public class ContactCreationTest extends TestBase{



  @Test
  public void testContactCreation() throws Exception {
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Ivanov", "Ivan", "Ivanovich",
            "Ivan_01", "Moscow Red squre 1 h1", "+79153925555", "Test1"), true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before+1);
  }


}
