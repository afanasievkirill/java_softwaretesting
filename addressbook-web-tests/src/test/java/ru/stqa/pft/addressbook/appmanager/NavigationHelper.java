package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{


  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("groups")
            && isElementPresent(By.name("new"))){
      click(By.linkText("groups"));
      return;
    }
    click(By.linkText("groups"));
  }

  public void returnToContactCreation() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void ContactPage() {
    click(By.linkText("home"));
  }


}
