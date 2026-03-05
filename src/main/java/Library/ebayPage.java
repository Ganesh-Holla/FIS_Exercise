package Library;

import Base.UI_Base;
import com.microsoft.playwright.Page;

public class ebayPage extends UI_Base {
    ebayPageObjects epo;
    Page page;
    public ebayPage(Page page){
        this.page = page;
        epo = new ebayPageObjects(page);
    }
    public void ebaySearchAction(String book){
        clickField(epo.searchfield());
        enterText(epo.searchfield(), book);

    }
}
