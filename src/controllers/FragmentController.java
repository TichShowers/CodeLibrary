package controllers;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import models.Comment;
import models.Fragment;
import models.Language;
import persistence.CommentDao;
import persistence.FragmentDao;
import persistence.LanguageDao;
import viewmodels.FragementView;
import viewmodels.FragmentEdit;
import mvc.controllers.ResourceController;
import mvc.responses.ActionResult;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class FragmentController extends ResourceController {

	private FragmentDao fragmentDao;
	private LanguageDao languageDao;
	private CommentDao commentDao;
	
	@Override
	public void initialize(Connection connection) {
		fragmentDao = new FragmentDao(connection);
		languageDao = new LanguageDao(connection);
		commentDao = new CommentDao(connection);
	}

	@Override
	public ActionResult index() {
		List<Fragment> fragments = fragmentDao.readAllWithNumberOfComments(); 
		return view("fragment-list.jsp", fragments);
	}

	@Override
	public ActionResult show(int index) {
		Fragment fragment = fragmentDao.read(index);
		
		//List<Comment> comments = commentDao.search("fragment", fragment.getId());
		List<Comment> comments = commentDao.searchWithNewestFirst(fragment.getId());
 		
		Language langauge = languageDao.read(fragment.getLanguage());
		
		return view("fragment-show.jsp", new FragementView(fragment, langauge, comments));
	}

	@Override
	public ActionResult create() {
		List<Language> languages = languageDao.readAll();
		
		return view("fragment-edit.jsp", new FragmentEdit(languages));
	}

	@Override
	public ActionResult edit(int index) {
		List<Language> languages = languageDao.readAll();
		Fragment fragment = fragmentDao.read(index);
		
		return view("fragment-edit.jsp", new FragmentEdit(fragment, languages));
	}

	@Override
	public ActionResult delete(int index) {
		fragmentDao.remove(index);
		return redirect("fragment");
	}
	
	@Override
	public ActionResult save(int index) {
		Fragment fragment = fragmentDao.read(index);
		
		fragment.setTitle(getParam("title", ""));
		fragment.setCode(getParam("code", ""));
		fragment.setLanguage(getParam("language", 0));
		
		fragmentDao.update(fragment);
		
		return redirect("fragment");
	}
	
	@Override
	public ActionResult save() {
		Fragment fragment = new Fragment(0, getParam("title", ""), getParam("code", ""), new Date() , getParam("language", 0));
		
		fragmentDao.insert(fragment);
		
		return redirect("fragment");
	}
}
