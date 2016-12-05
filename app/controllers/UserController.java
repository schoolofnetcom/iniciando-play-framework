package controllers;

import java.util.List;

import com.google.inject.Inject;

import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.helper.form;
import views.html.*;

public class UserController extends Controller {
	@Inject
	private FormFactory formFactory;
	private Form<User> formUser = null;
	
	public Result findAll() {
		List<User> list = User.find.all();
		
		return ok(users.render(list));
	}
	
	public Result findOne(Long id) {	
		User one = User.find.byId(id);
		
		return ok(user.render(one));
	}
	
	public Result create() {
		formUser = formFactory.form(User.class);
		
		return ok(user_create.render(formUser));
	}
	
	public Result save() {
		User user = formUser.bindFromRequest().get();
		
		user.save();
		
		return redirect("/users");
	}
	
	public Result remove(Long id) {
		User user = User.find.byId(id);
		
		user.delete();
		
		return redirect("/users");
	}
}
