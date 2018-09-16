package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  protected FirefoxDriver wd;

  private GroupHelper groupHelper;

  public void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    groupHelper = new GroupHelper(wd);
    Login("admin", "secret");
  }

  private void Login(String s, String password) {
    groupHelper.wd.findElement(By.name("user")).clear();
    groupHelper.wd.findElement(By.name("user")).sendKeys(s);
    groupHelper.wd.findElement(By.name("pass")).click();
    groupHelper.wd.findElement(By.name("pass")).clear();
    groupHelper.wd.findElement(By.name("pass")).sendKeys(password);
    groupHelper.wd.findElement(By.xpath("//input[@value='Login']")).click();
    groupHelper.wd.findElement(By.xpath("//body")).click();
  }

  public void gotoGroupPage() {
    groupHelper.wd.findElement(By.linkText("groups")).click();
  }

  public void stop() {
    groupHelper.wd.findElement(By.linkText("Logout")).click();
    groupHelper.wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }
}
