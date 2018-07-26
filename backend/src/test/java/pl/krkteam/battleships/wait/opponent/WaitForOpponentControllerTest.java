package pl.krkteam.battleships.wait.opponent;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.wait.opponent.dto.OpponentAbsentDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentPresentDTO;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WaitForOpponentControllerTest {
    private WaitForOpponentController waitForOpponentController;

    @Mock
    private RoomHolder roomHolder;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        waitForOpponentController = new WaitForOpponentController(roomHolder);

        mockMvc = MockMvcBuilders.standaloneSetup(waitForOpponentController).build();
    }

    @Test
    public void testIsOpponentPresentAndExpectPresent() throws Exception {
        String roomName = "someRoom";
        String waitingPlayer = "SomePlayer";
        when(roomHolder.isOpponentInRoom(eq(roomName), any(Player.class)))
                .thenReturn(new OpponentPresentDTO());

        mockMvc.perform(get("/room/opponent/present")
                .param("playerName", waitingPlayer)
                .param("roomName", roomName))
                .andExpect(status()
                        .is2xxSuccessful());

        verify(roomHolder, times(1)).isOpponentInRoom(anyString(), any());
    }

    @Test
    public void testIsOpponentPresentAndExpectAbsent() throws Exception {
        String roomName = "someRoom";
        String waitingPlayer = "SomePlayer";
        when(roomHolder.isOpponentInRoom(eq(roomName), any(Player.class)))
                .thenReturn(new OpponentAbsentDTO());

        mockMvc.perform(get("/room/opponent/present")
                .param("playerName", waitingPlayer)
                .param("roomName", roomName))
                .andExpect(status()
                        .is2xxSuccessful());

        verify(roomHolder, times(1)).isOpponentInRoom(anyString(), any());
    }
}