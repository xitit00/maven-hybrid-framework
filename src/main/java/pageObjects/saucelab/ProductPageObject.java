package pageObjects.saucelab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.BasePage;
import pageUIs.saucelab.ProductPageUI;

public class ProductPageObject extends BasePage {
	
	public WebDriver driver;

	public ProductPageObject(WebDriver driver) {
	
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String textItem) {
		// TODO Auto-generated method stub
		
		waitForElementClickable(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN);
		
		selectItemByTextInDefaultDropdown(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN, textItem);
		
	}

	public boolean isProductNameSortByAscending() {
		
		//B1: getText của all product name ra 
		List<WebElement> elementList = getListWebElement(driver, ProductPageUI.INVENTORY_ITEM_NAME);
		
		//B2: khởi tạo mảng arrayList <String> A và add vào để lưu giữ product name ( lúc này chưa biết array A đúng hay sai nhé )
		List<String> arrayList = new ArrayList<>();
		
		for (WebElement element : elementList) {
			
			arrayList.add(element.getText());
			System.out.println("Product name UI: " + element.getText());
		}
		
		//B3: khởi tạo arrayList<String> B để copy data từ arrayList<String> B để sort  
		// sort	arrayList<String> B ( chắc chắn array B là đúng , vì hàm sort là của java chuẩn nhất r )
		
		List<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			
			sortedList.add(child);
		}
		// sort function
		Collections.sort(sortedList);
		
		for (String productSorted : sortedList) {
			
			System.out.println("Product name sort by Asc: " + productSorted);
		}
		
		//B4 : verify 2 mảng array A vs array B , giống nhau là đúng , khác nhau là sai
		return sortedList.equals(arrayList);
	}

	public boolean isProductNameSortByDescending() {
	
		List<WebElement> elementList = getListWebElement(driver, ProductPageUI.INVENTORY_ITEM_NAME);

		List<String> arrayList = new ArrayList<>();

		for (WebElement element : elementList) {

			System.out.println("Product name UI: " + element.getText());
			arrayList.add(element.getText());
		}

		List<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {

			sortedList.add(child);
		}
		
		// sort asc 
		Collections.sort(sortedList);
	
		for (String productSorted : sortedList) {
			
			System.out.println("Product name sort by Asc: " + productSorted);
		}
		
		// sort desc 
		Collections.reverse(sortedList);
		for (String productSorted : sortedList) {
			
			System.out.println("Product name sort by Desc: " + productSorted);
		}
		
		return sortedList.equals(arrayList);
	}

	public boolean isProductPriceSortbyAscending() {
		// TODO Auto-generated method stub
		
		List<WebElement> elementList = getListWebElement(driver, ProductPageUI.INVENTORY_ITEM_PRICE);
		
		List<Float> arrayList = new ArrayList<>();
		
		for (WebElement element : elementList) {
			
			// get element priceText
			String priceText = element.getText();
			System.out.println("Product price UI: " + priceText);
			
			// replace "$" -> ""
			priceText = priceText.replace("$", "");
			System.out.println("Replace price Text UI: " + priceText);
			
			// convert string to float
			Float priceTextFloat = Float.parseFloat(priceText);
			arrayList.add(priceTextFloat);
			
		}
		
		
		List<Float> sortedList = new ArrayList<>();
		for (float price : arrayList) {
			
			sortedList.add(price);
		}
		// sort function
		Collections.sort(sortedList);
		
		for (float productSorted : sortedList) {
			
			System.out.println("Product price sort by Asc: " + productSorted);
		}
		
		return sortedList.equals(arrayList);
		
	}

	public boolean isProductPriceSortbyDescending() {
		
		List<WebElement> elementList = getListWebElement(driver, ProductPageUI.INVENTORY_ITEM_PRICE);
		
		//B2: khởi tạo mảng arrayList <String> A và add vào để lưu giữ product name ( lúc này chưa biết array A đúng hay sai nhé )
		List<Float> arrayList = new ArrayList<>();
		
		for (WebElement element : elementList) {
		
			// get element priceText
			String priceText = element.getText();
			System.out.println("Product price UI: " + priceText);
			
			// replace "$" -> ""
			priceText = priceText.replace("$", "");
			System.out.println("Replace price Text : " + priceText);
			
			// convert string to float
			Float priceTextFloat = Float.parseFloat(priceText);
			arrayList.add(priceTextFloat);
			
		}
		
		//B3: khởi tạo arrayList<String> B để copy data từ arrayList<String> B để sort  
		// sort	arrayList<String> B ( chắc chắn array B là đúng , vì hàm sort là của java chuẩn nhất r )
		
		List<Float> sortedList = new ArrayList<>();
		for (float price : arrayList) {
			
			sortedList.add(price);
		}
		// sort asc
		Collections.sort(sortedList);
		
		for (float productSorted : sortedList) {
			
			System.out.println("Price price sort by Asc: " + productSorted);
		}
		
		
		// sort desc 
		Collections.reverse(sortedList);
		for (float productSorted : sortedList) {

			System.out.println("Product price sort by Desc: " + productSorted);
		}
		
	
		//B4 : verify 2 mảng array A vs array B , giống nhau là đúng , khác nhau là sai
		return sortedList.equals(arrayList);
	}
	
	

}
