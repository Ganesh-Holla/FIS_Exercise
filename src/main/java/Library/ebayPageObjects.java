package Library;

import Base.UI_Base;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ebayPageObjects extends UI_Base {
    Page page;
    public ebayPageObjects(Page page){
        this.page = page;
    }

    public Locator searchfield(){
        return getLocator(page, "//input[@placeholder='Search for anything']");
    }
    public Locator searchButton(){
        return getLocator(page, "//button[@id='gh-search-btn']");
    }
    public Locator firstBook() {
        return getLocator(page, "ul.srp-results li.s-item a.s-item__link");
    }
    public Locator addToCart(){
        return getLocator(page,"(//a[@class='ux-call-to-action fake-btn fake-btn--fluid fake-btn--large fake-btn--secondary'])[2]");
    }
}
