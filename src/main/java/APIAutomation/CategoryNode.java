package APIAutomation;

import java.util.List;

public class CategoryNode {
	private Integer categoryId;
	private String key;
	private String url;
	private String name;
	private Boolean includeInMenu;
	private int level;
	private String featureLink;
	private List<CategoryNode> children;
	private List<String> availableSortBy;
	private String image;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIncludeInMenu() {
		return includeInMenu;
	}
	public void setIncludeInMenu(Boolean includeInMenu) {
		this.includeInMenu = includeInMenu;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getFeatureLink() {
		return featureLink;
	}
	public void setFeatureLink(String featureLink) {
		this.featureLink = featureLink;
	}
	public List<CategoryNode> getChildren() {
		return children;
	}
	public void setChildren(List<CategoryNode> children) {
		this.children = children;
	}
	public List<String> getAvailableSortBy() {
		return availableSortBy;
	}
	public void setAvailableSortBy(List<String> availableSortBy) {
		this.availableSortBy = availableSortBy;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
