package pl.krkteam.battleships.page.placing

import org.openqa.selenium.WebDriver

object FleetWaiter {

    fun waitGamePage(webDriver: WebDriver) {
        println()

        while (webDriver.currentUrl.contains("waiting/opponent/fleet")) {
            Thread.sleep(100)
        }
        println("Opponent has placed his fleet!")
    }
}