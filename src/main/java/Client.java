import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String name;
  private String appt_date;
  private String cut_request;
  private int stylist_id;

  public Client(String name, String apptDate, String cutRequest, int stylistId) {
    this.name = name;
    this.appt_date = apptDate;
    this.cut_request = cutRequest;
    this.stylist_id = stylistId;
  }

  public String getName() {
    return name;
  }

  public String getApptDate() {
    return appt_date;
  }

  public String getCut() {
    return cut_request;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
             this.getId() == newClient.getId();
    }
  }

  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients;";
      List<Client> allClients = con.createQuery(sql)
        .executeAndFetch(Client.class);
      return allClients;
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO clients (name, appt_date, cut_request, stylist_id) VALUES (:name, :appt_date, :cut_request, :stylist_id);";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("appt_date", this.appt_date)
      .addParameter("cut_request", this.cut_request)
      .addParameter("stylist_id", this.stylist_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id = :id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void update(String name, String appt_date, String cut_request) {
    String sql = "UPDATE clients SET name=:name, appt_date=:apptDate, cut_request=:cutRequest WHERE id=:id;";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("apptDate", appt_date)
        .addParameter("cutRequest", cut_request)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void updateStylist(int stylist_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "Update clients SET stylist_id=:stylist_id WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM clients WHERE id=:id;";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public static long getAllOrphans() {
    long count;
    String sql = "SELECT COUNT(*) FROM clients WHERE stylist_id not in (SELECT id FROM stylists);";
    try(Connection con = DB.sql2o.open()) {
      count = (long) con.createQuery(sql).executeScalar();
    }
    return count;
  }

  public static List<Client> search(String input) {
  String newInput = "%" + input + "%";
  try (Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients WHERE lower(name) LIKE lower(:newInput);";
    List<Client> foundClients = con.createQuery(sql)
      .addParameter("newInput", newInput)
      .executeAndFetch(Client.class);
    return foundClients;
    }
  }
}
