package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
public class ContactCreationTest extends TestBase{



  @Test
  public void testContactCreation() throws Exception {
    app.initContactCreation();
    app.fillContactForm(new ContactData("Ivanov", "Ivan", "Ivanovich", "Ivan_01", "Moscow Red squre 1 h1", "+79153925555"));
    app.submitContactCreation();
    app.returnToContactCreation();

  }


}
