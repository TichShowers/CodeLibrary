package viewmodels;

import java.util.List;

import models.Fragment;
import models.Language;

public class FragmentEdit {
	private Fragment fragment;
	private List<Language> languages;
	public Fragment getFragment() {
		return fragment;
	}
	public void setFragment(Fragment fragment) {
		this.fragment = fragment;
	}
	public List<Language> getLanguages() {
		return languages;
	}
	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	public FragmentEdit(Fragment fragment, List<Language> languages) {
		this.fragment = fragment;
		this.languages = languages;
	}
	public FragmentEdit(List<Language> languages) {
		this(null, languages);
	}
	
	
}
