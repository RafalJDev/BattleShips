package pl.krkteam.battleships.page.placing

import org.openqa.selenium.WebDriver
import pl.krkteam.battleships.web.Clicker

object FleetPlacer {

    fun place(webDriver: WebDriver) {
        Clicker.clickOn(webDriver, "random-fleet", 0)
        Thread.sleep(150)
        Clicker.clickOn(webDriver, "send-ships", 0)
    }
}