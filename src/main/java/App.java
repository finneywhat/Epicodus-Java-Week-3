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
      model.put("orphans", Client.getAllOrphans());
      model.put("clients", Client.all());
      model.put("stylists", Stylist.all());
      model.put("stylistsIDs", Stylist.allIDs());
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
      response.redirect("/");
      model.put("stylist", newStylist);
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

    post("/stylists/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String name = request.queryParams("name");
      String hireDate = request.queryParams("hireDate");
      String salary = request.queryParams("salary");
      String schedule = request.queryParams("schedule");
      stylist.update(name, hireDate, salary, schedule);
      // String url = String.format("/stylists/%d", stylist.getId());
      // response.redirect(url);
      response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      stylist.delete();
      // model.put("template", "templates/index.vtl");
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:stylistId/client/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      String name = request.queryParams("name");
      String apptDate = request.queryParams("apptDate");
      String cutType = request.queryParams("cutType");
      Client newClient = new Client(name, apptDate, cutType, stylist.getId());
      newClient.save();
      model.put("clients", stylist.getClients());
      model.put("stylist", stylist);
      model.put("client", newClient);
      // String url = String.format("/stylists/%d", stylist.getId());
      // response.redirect(url);
      response.redirect(request.headers("Referer"));
      // model.put("template", "templates/stylist-info.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:stylistId/clients/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("stylists", Stylist.all());
      model.put("client", client);
      model.put("template", "templates/client-info.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:stylistId/client/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      model.put("stylist", stylist);
      model.put("template", "templates/client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:stylistId/clients/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      // Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      // Stylist stylist = Stylist.find(client.getStylistId());
      int chgStylist = Integer.parseInt(request.queryParams("chgStylist"));
      Stylist newStylist = Stylist.find(chgStylist);
      String name = request.queryParams("name");
      String apptDate = request.queryParams("apptDate");
      String cutType = request.queryParams("cutType");
      client.update(name, apptDate, cutType);
      client.updateStylist(chgStylist);
      String url = String.format("/stylists/%d/clients/%d", newStylist.getId(), client.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:stylistId/clients/:clientId/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylistId")));
      Client client = Client.find(Integer.parseInt(request.params(":clientId")));
      client.delete();
      String url = String.format("/stylists/%d", stylist.getId());
      response.redirect(url);
      model.put("template", "templates/stylist-info.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:stylistId/clients/success", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int stylistID = Integer.parseInt(request.params(":stylistId"));
      Stylist stylist = Stylist.find(stylistID);
      String name = request.queryParams("name");
      String apptDate = request.queryParams("apptDate");
      String cutType = request.queryParams("cutType");
      Client client = new Client(name, apptDate, cutType, stylistID);
      client.save();
      model.put("clients", stylist.getClients());
      model.put("client", client);
      model.put("stylist", stylist);
      String url = String.format("/stylists/%d", stylist.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:stylistId/clients/:id/reassign", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.queryParams("orphanClient")));
      int stylistInt = Integer.parseInt(request.queryParams("stylistNewClient"));
      Stylist stylist = Stylist.find(stylistInt);
      client.updateStylist(stylistInt);
      model.put("client", client);
      model.put("stylist", stylist);
      // model.put("stylists", Stylist.all());
      // model.put("clients", Client.all());
      // String url = String.format("/stylists/%d", stylist.getId());
      // response.redirect(url);
      response.redirect(request.headers("Referer"));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/search-results", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String searchInput = request.queryParams("searchInput");
      model.put("clients", Client.search(searchInput));
      model.put("stylists", Stylist.search(searchInput));
      model.put("template", "templates/search-results.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
