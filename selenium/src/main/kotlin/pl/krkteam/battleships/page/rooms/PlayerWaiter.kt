package pl.krkteam.battleships.page.rooms

import org.openqa.selenium.WebDriver

object PlayerWaiter {

    fun waitFleetPage(webDriver: WebDriver) {
        println()

        while (webDriver.currentUrl.contains("waiting/opponent/register")) {
            Thread.sleep(100)
        }
        println("There is player ! Now I will place fleet !")
    }
}