import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/stylist-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String hireDate = request.queryParams("hire-date");
      String salary = request.queryParams("salary");
      String schedule = request.queryParams("schedule");
      Stylist newStylist = new Stylist(name, hireDate, salary, schedule);
      newStylist.save();
      model.put("stylist", newStylist);
      model.put("template", "templates/stylist-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("clients", stylist.getClients());
      model.put("stylist", stylist);
      model.put("template", "templates/stylist-info.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      String name = request.queryParams("name");
      String apptDate = request.queryParams("apptDate");
      String cutType = request.queryParams("cutType");
      Client newClient = new Client(name, apptDate, cutType, stylist.getId());
      newClient.save();
      model.put("stylist", stylist);
      model.put("client", newClient);
      model.put("template", "templates/stylist-client-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
