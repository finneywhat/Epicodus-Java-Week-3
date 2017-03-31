import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Stylist {
  private int id;
  private String name;
  private String hire_date;
  private String base_salary;
  private String work_schedule;

  public Stylist(String name, String hire_date, String base_salary, String work_schedule) {
    this.name = name;
    this.hire_date = hire_date;
    this.base_salary = base_salary;
    this.work_schedule = work_schedule;
    // employed = true;
  }

  public String getName() {
    return name;
  }

  public String getHireDate() {
    return hire_date;
  }

  public String getSalary() {
    return base_salary;
  }

  public String getSchedule() {
    return work_schedule;
  }

  public int getId() {
    return id;
  }

  public List<Client> getClients() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients WHERE stylist_id = :id";
    return con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(Client.class);
  }
}
}
