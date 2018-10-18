package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTestDB extends TestBase{

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); //list<GroupData>.class
    return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/cumis.png");
    ContactData contact = new ContactData().
            withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withAddress("Moscow Red squre 1 h1").
            withHomephone("+79153925555").withNickname("Ivan_01").withGroup("Test1").withPhoto(photo);
    app.contact().create(contact, true);
    Contacts after = app.db().contacts();
  //  assertThat(after.size(), equalTo(before.size()+1));
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

  @Test (dataProvider = "validGroupsFromJson")
  public void testContactCreationJson(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }


}
