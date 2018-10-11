package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
    list.add(new Object[]{new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
    list.add(new Object[]{new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
    return list.iterator();
  }

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test 2");
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(app.group().count(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before
            .withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test 2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }

  @Test(enabled = false)
  //Недостатки: метод не информирует о тестовых данных, падает на негативных данных.
  public void testGroupCreationMs() throws Exception {
    String[] names = new String[]{"test1", "test2", "test3"};
    for (String name : names) {
      app.goTo().groupPage();
      Groups before = app.group().all();
      GroupData group = new GroupData().withName(name);
      app.group().create(group);
      Groups after = app.group().all();
      assertThat(app.group().count(), equalTo(before.size() + 1));
      assertThat(after, equalTo(before
              .withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
  }

  @Test(dataProvider = "validGroups")
  //метод лишен недостатков testGroupCreationMs(). данные явно отображены, негативные тесты проходят.
  //данные для отчета полчучаются из ContactData метода toSting.
  public void testGroupCreationDP(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(app.group().count(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before
            .withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

}
