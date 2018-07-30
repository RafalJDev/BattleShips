package pl.krkteam.battleships.web

import org.openqa.selenium.WebDriver

object ChromeOpener {

    fun openChrome(webDriver: WebDriver, url: String) {
        webDriver.get(url)
    }
}