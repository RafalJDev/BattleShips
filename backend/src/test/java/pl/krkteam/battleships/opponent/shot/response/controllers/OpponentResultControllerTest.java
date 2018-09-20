package pl.krkteam.battleships.opponent.shot.response.controllers;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentNoShot;
import pl.krkteam.battleships.room.holder.Room;
import pl.krkteam.battleships.room.holder.RoomHolder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OpponentResultControllerTest {

    OpponentResultController opponentResultController;

    @Mock
    RoomHolder roomHolder;

    MockMvc mockMvc;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.initMocks(this);

        opponentResultController = new OpponentResultController(roomHolder);

        mockMvc = MockMvcBuilders.standaloneSetup(opponentResultController).build();
    }

    @Test
    void testOnShotOpponentResponse() throws Exception {
        Room room = new Room("SomeRoom");
        Player playerA = new Player("SomePlayerA");
        Player playerB = new Player("SomePlayerB");
        room.joinPlayer(playerA);
        room.joinPlayer(playerB);
        room.areBothPlayers();
        room.getGame().addShotResultToQueue(playerA, new OpponentNoShot());

        when(roomHolder.getRoom("SomeRoom")).thenReturn(room);

        mockMvc.perform(get("/game/opponent/result")
                .param("playerName", "SomePlayerA")
                .param("roomName", "SomeRoom"))
                .andExpect(status()
                        .is2xxSuccessful());
    }
}