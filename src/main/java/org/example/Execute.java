package org.example;

import Base.UI_Base;
import Library.ebayPage;
import Library.ebayPageObjects;
import com.microsoft.playwright.Page;


public class Execute {
    static UI_Base base = new UI_Base();
    public static Page page;
    ebayPageObjects ebpo = new ebayPageObjects(page);
    static ebayPage ebpage = new ebayPage(page);

    public static void main(String[] args) {
//        Execute mn = new Execute();
        page = base.launchBrowser();
        base.launchURL("https://www.ebay.com/", page);
        ebpage.ebaySearchAction("book");
    }
}

