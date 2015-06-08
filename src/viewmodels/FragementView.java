package viewmodels;

import java.util.List;

import models.Comment;
import models.Fragment;
import models.Language;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class FragementView {
	private Fragment fragment;
	private List<Comment> comments;
	private Language language;

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Fragment getFragment() {
		return fragment;
	}

	public void setFragment(Fragment fragment) {
		this.fragment = fragment;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public FragementView(Fragment fragment, Language language,
			List<Comment> comments) {
		this.fragment = fragment;
		this.language = language;
		this.comments = comments;
	}
}
