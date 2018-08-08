package pl.krkteam.battleships.controller

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import pl.krkteam.battleships.page.game.Shooter
import pl.krkteam.battleships.page.placing.FleetPlacer
import pl.krkteam.battleships.page.placing.FleetWaiter
import pl.krkteam.battleships.page.register.PlayerRegistration
import pl.krkteam.battleships.page.rooms.PlayerWaiter
import pl.krkteam.battleships.page.rooms.RoomCreator
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

    fun runIt() {
        openChrome()

        PlayerRegistration.provideNameAndGoToRooms(webDriver)

        RoomCreator.createRoom(webDriver)
        PlayerWaiter.waitFleetPage(webDriver)

        FleetPlacer.place(webDriver)
        FleetWaiter.waitGamePage(webDriver)

        Shooter().play(webDriver)
    }

    private fun openChrome() {
        ChromeOpener.openChrome(webDriver, "localhost:4100")
        Waiter.waitTillPageLoads(webDriver)
    }

}