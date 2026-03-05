package Base;

import com.microsoft.playwright.*;

public class UI_Base {
    public Page launchBrowser(){
        Playwright pwt = Playwright.create();
        BrowserType bt = pwt.chromium();
        Browser br = bt.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setSlowMo(2000));
        BrowserContext bc = br.newContext();
        Page page = bc.newPage();
        return page;
    }

    public void launchURL(String URL, Page page){
        page.navigate(URL);
    }
    public Locator getLocator(Page page,String locator_Expression){
        Locator loc;
        loc = page.locator(locator_Expression);
        return loc;
    }
    public void enterText(Locator locator,String text){
        locator.fill(text);
    }

    public void clickField(Locator locator){
        locator.click();
    }
//    public void closeBrowser(){
//        page.close();
//        browser.close();
//        pwt.close();
//    }
}
