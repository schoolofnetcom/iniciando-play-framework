package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;

import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.db.Database;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import views.html.*;

public class HelloController extends Controller {
	
	@Inject
	private FormFactory formFactory;
	public Form<User> userF = null;
	
	private Database db;
	
	@Inject
	public HelloController(Database db) {
		this.db = db;
	}

	public Result hello(String name) {
		db.getConnection();
		User.find.all();
		return ok("Hello " + name + " from Play Framework by School of net");
	}
	
	@BodyParser.Of(BodyParser.Text.class)
	public Result body() {
		RequestBody body = request().body();
		return ok(body.asText());
	}
	
	public Result res() {
		List<String> obj = new ArrayList<String>();
		
		
		obj.add("Leonan");
		obj.add("Luppi");
		
		
		JsonNode json = Json.toJson(obj);
//		return ok("<h1>Play framework by School of net</h1>").as("text/html");
		
//		return (json);

//		return redirect("/");
		
		userF = formFactory.form(User.class);
		
		return ok(hello.render("Leonan", "School of net", obj, userF));
	}
	
	public Result form() {
		User user = userF.bindFromRequest().get();
		return ok(user.getName().toString());
	}	
}
