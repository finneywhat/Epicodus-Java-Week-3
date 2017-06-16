import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

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
  public void getClients_returnsListOfAllClients_List() {
    Stylist testStylist = new Stylist("Dennis", "02-14-10", "30,000", "M-F");
    testStylist.save();
    Client clientOne = new Client("Chris", "03-31-17", "low-fade", testStylist.getId());
    clientOne.save();
    Client clientTwo = new Client("Dennis", "03-31-17", "fade", testStylist.getId());
    clientTwo.save();
    Client[] allClients = new Client[] {clientOne, clientTwo};
    assertEquals(true, testStylist.getClients().containsAll(Arrays.asList(allClients)));
  }

  @Test
  public void getDaysEmployed_returnsTimeBetweenHireDateAndCurrentDate() {
    
  }
}
