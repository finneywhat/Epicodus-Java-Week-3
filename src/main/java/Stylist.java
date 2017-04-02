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

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
             this.getId() == newStylist.getId();
    }
  }

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists;";
      List<Stylist> allStylists = con.createQuery(sql)
        .executeAndFetch(Stylist.class);
      return allStylists;
    }
  }

  public static List<Integer> allIDs() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id FROM stylists;";
      List<Integer> allStylistsIDs = con.createQuery(sql)
        .executeAndFetch(Integer.class);
      return allStylistsIDs;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, hire_date, base_salary, work_schedule) VALUES (:name, :hire_date, :base_salary, :work_schedule);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("hire_date", this.hire_date)
        .addParameter("base_salary", this.base_salary)
        .addParameter("work_schedule", this.work_schedule)
        .executeUpdate()
        .getKey();
      }
    }

    public static Stylist find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM stylists WHERE id=:id;";
        Stylist stylist = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Stylist.class);
        return stylist;
      }
    }

    public void update(String name, String hire_date, String base_salary, String work_schedule) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE stylists SET name=:name, hire_date=:hire_date, base_salary=:base_salary, work_schedule=:work_schedule WHERE id=:id;";
        con.createQuery(sql)
          .addParameter("name", name)
          .addParameter("hire_date", hire_date)
          .addParameter("base_salary", base_salary)
          .addParameter("work_schedule", work_schedule)
          .addParameter("id", id)
          .executeUpdate();
      }
    }

    public void delete() {
      try (Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM stylists WHERE id=:id;";
        con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
      }
    }
}
