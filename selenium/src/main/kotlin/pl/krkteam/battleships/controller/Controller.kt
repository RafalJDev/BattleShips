package pl.krkteam.battleships.controller

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import pl.krkteam.battleships.web.ChromeOpener
import pl.krkteam.battleships.web.Waiter
import java.nio.file.FileSystems

class Controller {

    private var webDriver: WebDriver

    constructor() {
        var pathToChrome = FileSystems.getDefault().getPath("").toAbsolutePath().toString()

        pathToChrome += "/selenium/chromeDriver/chromedriver"

        System.setProperty("webdriver.chrome.driver", pathToChrome)

        webDriver = ChromeDriver()
    }

    fun openChrome() {
        ChromeOpener.openChrome(webDriver, "https://justjoin.it/krakow/java/")

        Waiter.waitTillPageLoads(webDriver)
    }

}