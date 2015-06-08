package controllers;

import java.sql.Connection;
import java.util.List;

import persistence.LanguageDao;
import models.Language;
import mvc.controllers.ResourceController;
import mvc.responses.ActionResult;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class LanguageController extends ResourceController {

	private LanguageDao dao;
	
	@Override
	public void initialize(Connection connection) {
		dao = new LanguageDao(connection);
	}

	@Override
	public ActionResult index() {
		List<Language> languages = dao.readAll();
		return view("language-list.jsp", languages);
	}

	@Override
	public ActionResult show(int index) {
		return redirect("language/new/"+ index);
	}

	@Override
	public ActionResult create() {
		return view("language-edit.jsp");
	}

	@Override
	public ActionResult edit(int index) {
		Language data = dao.read(index);
		
		return view("language-edit.jsp", data);
	}

	@Override
	public ActionResult delete(int index) {
		dao.remove(index);
		return redirect("language");
	}
	
	@Override
	public ActionResult save(int index) {
		Language data = dao.read(index);
		data.setName(getParam("name", ""));
		dao.update(data);
		
		return redirect("language");
	}
	
	@Override
	public ActionResult save() {
		Language data = new Language(0, getParam("name", ""));
		
		dao.insert(data);
		
		return redirect("language");
	}
}
