import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void client_instantiatesCorrectly_true() {
    Client myClient = new Client("Chris", "03-31-17", "low-fade", 1);
    assertEquals(true, myClient instanceof Client);
  }

  @Test
  public void getName_clientInstantiatesWithName_String() {
    Client myClient = new Client("Chris", "03-31-17", "low-fade", 1);
    assertEquals("Chris", myClient.getName());
  }

  @Test
  public void getApptDate_clientInstantiatesWithApptDate_String() {
    Client myClient = new Client("Chris", "03-31-17", "low-fade", 1);
    assertEquals("03-31-17", myClient.getApptDate());
  }

  @Test
  public void getCut_clientInstantiatesWithName_String() {
    Client myClient = new Client("Chris", "03-31-17", "low-fade", 1);
    assertEquals("low-fade", myClient.getCut());
  }

  @Test
  public void getStylistId_clientInstantiatesWithStylistId_int() {
    Client myClient = new Client("Chris", "03-31-17", "low-fade", 1);
    assertEquals(1, myClient.getStylistId());
  }

  @Test
  public void equals_returnsTrueIfObjectsAreSame_true() {
    Client clientOne = new Client("Chris", "03-31-17", "low-fade", 1);
    Client clientTwo = new Client("Chris", "03-31-17", "low-fade", 1);
    assertTrue(clientOne.equals(clientTwo));
  }

  @Test
  public void all_returnsAllIntancesOfClient() {
    Client clientOne = new Client("Chris", "03-31-17", "low-fade", 1);
    clientOne.save();
    Client clientTwo = new Client("Aaron", "03-31-17", "fade and beard-trim", 2);
    clientTwo.save();
    assertEquals(true, Client.all().get(0).equals(clientOne));
    assertEquals(true, Client.all().get(1).equals(clientTwo));
  }

  @Test
  public void find_returnClientObjectWithSameId() {
    Client clientOne = new Client("Chris", "03-31-17", "low-fade", 1);
    clientOne.save();
    Client clientTwo = new Client("Aaron", "03-31-17", "fade and beard-trim", 2);
    clientTwo.save();
    assertEquals(clientTwo, Client.find(clientTwo.getId()));
  }

  @Test
  public void save_assignsIdToObject() {
    Client clientOne = new Client("Chris", "03-31-17", "low-fade", 1);
    clientOne.save();
    Client savedClient = Client.all().get(0);
    assertEquals(clientOne.getId(), savedClient.getId());
  }

  @Test
  public void update_updatesContentsOfAnInstanceOfClient() {
    Client clientOne = new Client("Chris", "03-31-17", "low-fade", 1);
    clientOne.save();
    Client clientTwo = new Client("Aaron", "03-31-17", "fade and beard-trim", 2);
    clientTwo.save();
    clientOne.update("Dan", "04-02-17", "faded-fade");
    assertEquals("faded-fade", Client.find(clientOne.getId()).getCut());
  }

  @Test
  public void delete_deleteAClientObject() {
    Client clientOne = new Client("Chris", "03-31-17", "low-fade", 1);
    clientOne.save();
    Client clientTwo = new Client("Aaron", "03-31-17", "fade and beard-trim", 2);
    clientTwo.save();
    clientOne.delete();
    assertEquals(null, Client.find(clientOne.getId()));
  }

  @Test
  public void updateStylist_updatesStylistId() {
    Client clientOne = new Client("Chris", "03-31-17", "low-fade", 1);
    clientOne.save();
    clientOne.updateStylist(2);
    assertEquals(2, Client.find(clientOne.getId()).getStylistId());
  }

  @Test
  public void getAllOrphans_returnsTotalNumberOfClientsWithoutAStylist() {
    Stylist onlyStylist = new Stylist("Dennis", "02-14-10", "30,000", "Mon, Tue, Thur, Sat");
    onlyStylist.save();
    Client clientOne = new Client("Chris", "03-31-17", "low-fade", onlyStylist.getId());
    clientOne.save();
    Client clientTwo = new Client("Henry", "05-18-17", "low-fade", onlyStylist.getId());
    clientTwo.save();
    Client clientThree = new Client("John", "03-31-18", "fade", 12);
    clientThree.save();
    assertEquals(1, Client.getAllOrphans());
  }
}
