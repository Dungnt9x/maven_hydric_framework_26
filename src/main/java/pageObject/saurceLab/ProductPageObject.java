package pageObject.saurceLab;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.saurceLab.ProductPageUI;

public class ProductPageObject extends BasePage {
	WebDriver driver;
	
	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String textItem) {
		waitForElementClickTable(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN);
		selectItemInDefaultDropdow(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN, textItem);
		
	}

	public boolean isProductNameSortByAscending() {
		//Khai bao 1 array list để chứa các product name trên UI
		ArrayList<String> productUiList = new ArrayList<String>();
		//Lấy ra hết tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT); 
		//Dùng vòng lặp get text và add vào ArrayList trên
		for (WebElement productName : productNameText) {
			productUiList.add(productName.getText());
			System.out.println("Product name trên UI:" + productName.getText());
		}
		
		//Tạo ra 1 arrayList mới để so sánh với arrayList cũ xem có đúng không?
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUiList) {
			productSortList.add(product);
		}
		
		//Sort cái productSortList
		Collections.sort(productSortList);
		for (String productName : productSortList) {
			System.out.println("Product name trên UI:" + productName);
		}
		
		//So sánh 2 list đã bằng nhau
		return productSortList.equals(productUiList);
	}

	public boolean isProductNameSortByDescending() {
		//Khai bao 1 array list để chứa các product name trên UI
		ArrayList<String> productUiList = new ArrayList<String>();
		//Lấy ra hết tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT); 
		//Dùng vòng lặp get text và add vào ArrayList trên
		for (WebElement productName : productNameText) {
			productUiList.add(productName.getText());
			System.out.println("Product name trên UI:" + productName.getText());
		}
				
		//Tạo ra 1 arrayList mới để so sánh với arrayList cũ xem có đúng không?
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUiList) {
			productSortList.add(product);
		}
				
		//Sort cái productSortList
		Collections.sort(productSortList);
		for (String productName : productSortList) {
			System.out.println("Product name trên UI ASC:" + productName);
		}
				
		//Reverse cái productSortList
		Collections.reverse(productSortList);
		for (String productName : productSortList) {
			System.out.println("Product name trên UI DES:" + productName);
		}
				
		//So sánh 2 list đã bằng nhau
		return productSortList.equals(productUiList);
	}

	public boolean isProductPriceSortByAscending() {
	
		ArrayList<Float> productUiList = new ArrayList<Float>();
	
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT); 
		
		for (WebElement productPrice : productPriceText) {
			productUiList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
			System.out.println("Product Price trên UI:" + productPrice.getText());
		}

		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUiList) {
			productSortList.add(product);
		}

		Collections.sort(productSortList);
		for (Float productName : productSortList) {
			System.out.println("Product Price trên UI:" + productName);
		}

		return productSortList.equals(productUiList);
	}

	public boolean isProductPriceSortByDescending() {
		
		ArrayList<Float> productUiList = new ArrayList<Float>();
		
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT); 

		for (WebElement productPrice : productPriceText) {
			productUiList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
			System.out.println("Product Price trên UI:" + productPrice.getText());
		}
				
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUiList) {
			productSortList.add(product);
		}
				
		Collections.sort(productSortList);
		for (Float productPrice : productSortList) {
			System.out.println("Product Price trên UI ASC:" + productPrice);
		}
				
		Collections.reverse(productSortList);
		for (Float productPrice : productSortList) {
			System.out.println("Product Price trên UI DES:" + productPrice);
		}
				
		return productSortList.equals(productUiList);
	}
}
