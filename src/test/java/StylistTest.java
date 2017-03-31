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

  @Test
  public void equals_returnsTrueIfObjectsAreSame_true() {
    Stylist stylistOne = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    Stylist stylistTwo = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    assertTrue(stylistOne.equals(stylistTwo));
  }

  @Test
  public void all_returnsAllIntancesOfStylist() {
    Stylist stylistOne = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    stylistOne.save();
    Stylist stylistTwo = new Stylist("Liz", "01-11-11", "30,000", "Tue-Sun");
    stylistTwo.save();
    assertEquals(true, Stylist.all().contains(stylistOne));
    assertEquals(true, Stylist.all().contains(stylistTwo));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist stylistOne = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    stylistOne.save();
    Stylist stylistTwo = new Stylist("Liz", "01-11-11", "30,000", "Tue-Sun");
    stylistTwo.save();
    Stylist savedStylist = Stylist.all().get(1);
    assertEquals(stylistTwo.getId(), savedStylist.getId());
  }

  @Test
  public void find_returnStylistObjectWithSame_Stylist() {
    Stylist stylistOne = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    stylistOne.save();
    Stylist stylistTwo = new Stylist("Liz", "01-11-11", "30,000", "Tue-Sun");
    stylistTwo.save();
    assertEquals(stylistTwo, Stylist.find(stylistTwo.getId()));
  }

  @Test
  public void update_updatesContentsOfAnInstanceOfStylist() {
    Stylist stylistOne = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    stylistOne.save();
    Stylist stylistTwo = new Stylist("Liz", "01-11-11", "30,000", "Tue-Sun");
    stylistTwo.save();
    stylistOne.update("Dennis", "01-01-01", "40,000", "M-Th");
    assertEquals("01-01-01", Stylist.find(stylistOne.getId()).getHireDate());
  }

  @Test
  public void delete_deleteAnInstanceOfAStylist() {
    Stylist stylistOne = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    stylistOne.save();
    Stylist stylistTwo = new Stylist("Liz", "01-11-11", "30,000", "Tue-Sun");
    stylistTwo.save();
    stylistOne.delete();
    assertEquals(null, Stylist.find(stylistOne.getId()));
  }
}
