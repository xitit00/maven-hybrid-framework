package pageUIs.jQuery.uploadFile;

public class HomePageUI {

	// vì tất cả các site app đều dùng cái xpath này đc -> move nó qua BasePageJQueryUI
	//public static final String UPLOAD_FILE = "xpath=//input[@type='file']";
	
	// verify file loaded
	public static final String FILE_NAME_LOADED = "xpath=//p[@class='name' and text()='%s']"; 
	
	// 1/ là dùng xpath=//table//button[@class='btn btn-primary start']
	// 2/ là dùng css=table button.start
	public static final String START_BUTTON = "css=table button.start"; 
	
	// verify file uploaded by link 
	public static final String FILE_NAME_UPLOADED_LINK = "xpath=//a[text()='%s']";
	
	// verify file uploaded by Img 
	public static final String FILE_NAME_UPLOADED_IMG = "xpath=//a[@title='%s']/img";
	
	
	
}
