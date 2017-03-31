import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private String apptDate;
  private String cutType;
  private int stylistId;

  public Client(String name, String apptDate, String cutType, int stylistId) {
    this.name = name;
    this.apptDate = apptDate;
    this.cutType = cutType;
    this.stylistId = stylistId;
  }

  public String getName() {
    return name;
  }

  public String getApptDate() {
    return apptDate;
  }

  public String getCut() {
    return cutType;
  }

  public int getStylistId() {
    return stylistId;
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

}
