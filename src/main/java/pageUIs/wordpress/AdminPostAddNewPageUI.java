package pageUIs.wordpress;

public class AdminPostAddNewPageUI {
	
	public static final String TITLE_TEXTBOX = "css=h1.wp-block-post-title";
	//BODY_TEXTBOX_BEFORE_CLICK , role="button"
	public static final String BODY_BUTTON = "css=p.block-editor-default-block-appender__content";
	//BODY_TEXTBOX_AFTER_CLICK , role= "document"
	public static final String BODY_DOCUMENT = "css=p.block-editor-rich-text__editable";
	
	//chứa keyword
	//xpath=button[contains(@class,'editor-post-publish-button__button')]
	//css=button[class*='editor-post-publish-button__button'] 
	public static final String PUBLISH_BUTTON = "css=div[aria-label='Editor top bar'] button[class*='editor-post-publish-button__button']";
	public static final String PRE_PUBLISH_BUTTON = "css=div[aria-label='Editor publish'] button[class*='editor-post-publish-button__button']";
	
	public static final String PUBLISHED_MESSAGE = "xpath=//div[@class='components-snackbar__content' and text()='%s']";
	
}
