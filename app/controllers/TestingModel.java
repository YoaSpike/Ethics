package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import com.google.common.io.ByteStreams;

import play.*;
import play.mvc.*;
import play.data.Form;
import static play.data.Form.*;

import views.html.*;
import views.html.forms.*;
import views.formdata.*;
import models.*;

public class TestingModel extends Controller{
	public static Result testApplicationModel(){
			
			/*
			ApplicationModel applicationModel = new ApplicationModel();
			applicationModel.id = 20;
			*/
			UserModel userModel = new UserModel(20);
			//userModel.id = 20;

			//Section3 section3 = new Section3();

			UserModel user = UserModel.find.byId("20");

			return ok("Sup");
		}
}