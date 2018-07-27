package pl.krkteam.battleships.shooting.controller;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.shooting.services.ShotResultCheckerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShootingControllerTest {

    ShootingController shootingController;

    @Mock
    ShotResultCheckerService shotResultCheckerService;

    @Mock
    RoomHolder roomHolder;

    MockMvc mockMvc;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        shootingController = new ShootingController(shotResultCheckerService, roomHolder);

        mockMvc = MockMvcBuilders.standaloneSetup(shootingController).build();

    }

    @Test
    public void testValidateShotAndExpectClientError() throws Exception {
        mockMvc.perform(post("/game/player/shot")
                .content("")
                .param("playerName", "SomePlayer"))
                .andExpect(status()
                        .is4xxClientError());

    }
}
