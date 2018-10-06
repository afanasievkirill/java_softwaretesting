package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void returnToContactList() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactData(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click(); // выбор элемента по индексу не очень работающий
    //click(By.name("selected[]"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value = '"+id+"']")).click(); // выбор элемента по ID работающий
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    click(By.linkText("home")); //пауза
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click(); // выбор элемента по индексу
//    click(By.xpath("//img[@alt='Edit']"));
  }

  public void editContactById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click(); // выбор элемента по индексу
//    click(By.xpath("//img[@alt='Edit']"));
  }


  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contactData, boolean group) {
    initContactCreation();
    fillContactData(contactData, group);
    submitContactCreation();
    returnToContactList();
  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillContactData(contact, false);
    submitContactModification();
    returnToContactList();
  }

  public void delete(int index) {
    selectContact(index);
    deleteContact();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size(); //метод возвращает количество элементов в списке
  }

  public List<ContactData> list() {

    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {  //переменная пробегает по строкам таблицы
      List<WebElement> trs = element.findElements(By.tagName("td")); //массив из элементов строки
      List<String> strings = new ArrayList<String>(); //массив для преобразования вебэлемент в стринг
      for (WebElement e : trs) {
        strings.add(e.getText()); //метод переводит вебэлемент в стринг
      }
      String firstname = strings.get(2);
      String lastname = strings.get(1);
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact); //присвоение переменной возвращаемому массиву
    }
    return contacts;
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {  //переменная пробегает по строкам таблицы
      List<WebElement> trs = element.findElements(By.tagName("td")); //массив из элементов строки
      List<String> strings = new ArrayList<String>(); //массив для преобразования вебэлемент в стринг
      for (WebElement e : trs) {
        strings.add(e.getText()); //метод переводит вебэлемент в стринг
      }
      String firstname = strings.get(2);
      String lastname = strings.get(1);
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); //присвоение уникального идентификатора
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact); //присвоение переменной возвращаемому массиву
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    editContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back(); //команда на возврат драйверу.
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomephone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);
  }
}
