import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

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
  public void getStylistId_clientInstantiatesWithName_int() {
    Client myClient = new Client("Chris", "03-31-17", "low-fade", 1);
    assertEquals(1, myClient.getStylistId());
  }

  @Test

}

// save()
// all()
// delete()
// find()
// update()
// equals()
