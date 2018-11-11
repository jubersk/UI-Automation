package APIAutomation;

import java.util.ArrayList;

import org.testng.annotations.Test;

public class JsonResponse
{
	 private float categoryId;
	 private String key;
	 private String name;
	 private boolean includeInMenu;
	 private float level;
	 ArrayList < Object > children = new ArrayList < Object > ();
	 private String displayMode;
	 ArrayList < Object > availableSortBy = new ArrayList < Object > ();
	 private String fontColor;
	 private float excludeClearanceProduct;
	 private float excludePromotionalProduct;


	 // Getter Methods 

	 public float getCategoryId() {
	  return categoryId;
	 }

	 public String getKey() {
	  return key;
	 }

	 public String getName() {
	  return name;
	 }

	 public boolean getIncludeInMenu() {
	  return includeInMenu;
	 }

	 public float getLevel() {
	  return level;
	 }

	 public String getDisplayMode() {
	  return displayMode;
	 }

	 public String getFontColor() {
	  return fontColor;
	 }

	 public float getExcludeClearanceProduct() {
	  return excludeClearanceProduct;
	 }

	 public float getExcludePromotionalProduct() {
	  return excludePromotionalProduct;
	 }

	 // Setter Methods 

	 public void setCategoryId(float categoryId) {
	  this.categoryId = categoryId;
	 }

	 public void setKey(String key) {
	  this.key = key;
	 }

	 public void setName(String name) {
	  this.name = name;
	 }

	 public void setIncludeInMenu(boolean includeInMenu) {
	  this.includeInMenu = includeInMenu;
	 }

	 public void setLevel(float level) {
	  this.level = level;
	 }

	 public void setDisplayMode(String displayMode) {
	  this.displayMode = displayMode;
	 }

	 public void setFontColor(String fontColor) {
	  this.fontColor = fontColor;
	 }

	 public void setExcludeClearanceProduct(float excludeClearanceProduct) {
	  this.excludeClearanceProduct = excludeClearanceProduct;
	 }

	 public void setExcludePromotionalProduct(float excludePromotionalProduct) {
	  this.excludePromotionalProduct = excludePromotionalProduct;
	 }
}