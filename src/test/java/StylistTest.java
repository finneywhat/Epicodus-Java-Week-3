import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist stylist = new Stylist("Dennis", "02-14-10", "30,000", "Mon, Tue, Thur, Sat");
    assertTrue(stylist instanceof Stylist);
  }

  @Test
  public void getName_stylistInstantiatesWithName_String() {
    Stylist stylist = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    assertEquals("Dennis", stylist.getName());
  }

  @Test
  public void getHireDate_stylistInstantiatesWithHireDate_String() {
    Stylist stylist = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    assertEquals("02-14-10", stylist.getHireDate());
  }

  @Test
  public void getSalary_stylistInstantiatesWithSalary_String() {
    Stylist stylist = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    assertEquals("30,000", stylist.getSalary());
  }

  @Test
  public void getSchedule_stylistInstantiatesWithSchedule_String() {
    Stylist stylist = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    assertEquals("M-F", stylist.getSchedule());
  }

  @Test
  public void getClients_initiallyReturnsEmptyList_ArrayList() {
    Stylist stylist = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    assertEquals(0, stylist.getClients().size());
  }
}
