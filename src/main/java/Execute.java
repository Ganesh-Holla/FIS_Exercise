import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Execute {

    public static void main(String[] args) {
        Playwright pwt = Playwright.create();
        BrowserType bt = pwt.chromium();
        Browser br = bt.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setSlowMo(2000));
        BrowserContext bc = br.newContext();
        Page page = bc.newPage();

        page.navigate("https://www.ebay.com/");
        page.locator("//input[@placeholder='Search for anything']").fill("book");
        page.click("//button[@id='gh-search-btn']");
        page.click("(//span[@class='su-styled-text primary default'])[3]");
        Page newPage = bc.waitForPage(() -> {
            page.click("(//span[@class='su-styled-text primary default'])[3]");
        });
        newPage.waitForLoadState();
        newPage.getByText("Add to cart").click();
        newPage.onDialog(dialog -> {
            System.out.println("Alert message: " + dialog.message());
            dialog.dismiss();// Clicks OK
        });

        assertThat(newPage.locator("//span[@class='badge gh-badge']")).hasText("1");

        String valueInCart = newPage.locator("//span[@class='badge gh-badge']").textContent();
        if (valueInCart.equals("1")) {
            //newPage.bringToFront();
            System.out.println("Cart is added with one item ");
        } else
            System.out.println("Cart is not added with one item ");

        page.close();
        br.close();
        pwt.close();
    }
}
