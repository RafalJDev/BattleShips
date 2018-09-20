package pl.krkteam.battleships.shooting.controller;

import com.google.gson.Gson;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.CoordinateDTO;
import pl.krkteam.battleships.room.holder.Room;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.shooting.dto.ShotDTO;
import pl.krkteam.battleships.shooting.dto.result.*;
import pl.krkteam.battleships.shooting.services.ShotResultCheckerService;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.AssertJUnit.assertEquals;

public class ShootingControllerTest {

    ShootingController shootingController;

    @Mock
    ShotResultCheckerService shotResultCheckerService;

    @Mock
    RoomHolder roomHolder;

    MockMvc mockMvc;

    String roomName = "SomeRoom";
    Room room = new Room(roomName);
    Game game = room.getGame();
    Player playerA = new Player("PlayerA");
    Player playerB = new Player("PlayerB");


    @BeforeMethod
    void setUp() {
        MockitoAnnotations.initMocks(this);

        shootingController = new ShootingController(shotResultCheckerService, roomHolder);

        mockMvc = MockMvcBuilders.standaloneSetup(shootingController).build();
    }

    @BeforeMethod
    void reset() {
        this.roomName = "SomeRoom";
        this.room = new Room(roomName);
        this.game = room.getGame();
        this.playerA = new Player("PlayerA");
        this.playerB = new Player("PlayerB");
    }

    @Test
    void testValidateShotAndExpectHit() throws Exception {
        game.initializeGame(playerA, playerB);

        when(roomHolder.getRoom(eq(roomName))).thenReturn(room);
        ShotResultDTO expectedShotResult = new ResultMissDTO();

        when(shotResultCheckerService.checkShotResult(any(), any()))
                .thenReturn(expectedShotResult);

        ShotDTO shotDTO = new ShotDTO(new CoordinateDTO(3, 4));
        Gson gson = new Gson();
        final String shotJson = gson.toJson(shotDTO);
        final MvcResult mvcResult = mockMvc.perform(post("/game/player/shot")
                .content(shotJson)
                .param("playerName", "PlayerA")
                .param("roomName", roomName))
                .andExpect(status()
                        .is2xxSuccessful())
                .andReturn();

        verify(shotResultCheckerService, times(1)).checkShotResult(any(), any());
        assertEquals(mvcResult.getResponse().getContentAsString(), gson.toJson(expectedShotResult));

    }

    @Test
    void testValidateShotAndExpectShootMiss() throws Exception {
        game.initializeGame(playerA, playerB);

        when(roomHolder.getRoom(eq(roomName))).thenReturn(room);
        ShotResultDTO expectedShotResult = new ResultMissDTO();

        when(shotResultCheckerService.checkShotResult(any(), any()))
                .thenReturn(expectedShotResult);

        ShotDTO shotDTO = new ShotDTO(new CoordinateDTO(2, 7));
        Gson gson = new Gson();
        final String shotJson = gson.toJson(shotDTO);
        final MvcResult mvcResult = mockMvc.perform(post("/game/player/shot")
                .content(shotJson)
                .param("playerName", "PlayerA")
                .param("roomName", roomName))
                .andExpect(status()
                        .is2xxSuccessful())
                .andReturn();

        verify(shotResultCheckerService, times(1)).checkShotResult(any(), any());
        assertEquals(mvcResult.getResponse().getContentAsString(), gson.toJson(expectedShotResult));

    }

    @Test
    void testValidateShotAndExpectSunk() throws Exception {
        game.initializeGame(playerA, playerB);

        when(roomHolder.getRoom(eq(roomName))).thenReturn(room);
        ShotResultDTO expectedShotResult = new ResultSunkDTO();

        when(shotResultCheckerService.checkShotResult(any(), any()))
                .thenReturn(expectedShotResult);

        ShotDTO shotDTO = new ShotDTO(new CoordinateDTO(9, 4));
        Gson gson = new Gson();
        final String shotJson = gson.toJson(shotDTO);
        final MvcResult mvcResult = mockMvc.perform(post("/game/player/shot")
                .content(shotJson)
                .param("playerName", "PlayerA")
                .param("roomName", roomName))
                .andExpect(status()
                        .is2xxSuccessful())
                .andReturn();

        verify(shotResultCheckerService, times(1)).checkShotResult(any(), any());
        assertEquals(mvcResult.getResponse().getContentAsString(), gson.toJson(expectedShotResult));
    }

    @Test
    void testValidateShotAndExpectPlayerWon() throws Exception {
        game.initializeGame(playerA, playerB);

        when(roomHolder.getRoom(eq(roomName))).thenReturn(room);
        ShotResultDTO expectedShotResult = new ResultWrongShotDTO();

        when(shotResultCheckerService.checkShotResult(any(), any()))
                .thenReturn(expectedShotResult);

        ShotDTO shotDTO = new ShotDTO(new CoordinateDTO(3, 4));
        Gson gson = new Gson();
        final String shotJson = gson.toJson(shotDTO);
        final MvcResult mvcResult = mockMvc.perform(post("/game/player/shot")
                .content(shotJson)
                .param("playerName", "PlayerA")
                .param("roomName", roomName))
                .andExpect(status()
                        .is2xxSuccessful())
                .andReturn();

        verify(shotResultCheckerService, times(1)).checkShotResult(any(), any());
        assertEquals(mvcResult.getResponse().getContentAsString(), gson.toJson(expectedShotResult));
    }

    @Test
    void testValidateShotAndExpectNotYourTurn() throws Exception {
        game.initializeGame(playerA, playerB);

        when(roomHolder.getRoom(eq(roomName))).thenReturn(room);
        ShotResultDTO expectedShotResult = new NotYourTurnDTO();

        when(shotResultCheckerService.checkShotResult(any(), any()))
                .thenReturn(expectedShotResult);

        ShotDTO shotDTO = new ShotDTO(new CoordinateDTO(3, 4));
        Gson gson = new Gson();
        final String shotJson = gson.toJson(shotDTO);
        final MvcResult mvcResult = mockMvc.perform(post("/game/player/shot")
                .content(shotJson)
                .param("playerName", "PlayerB")
                .param("roomName", roomName))
                .andExpect(status()
                        .is2xxSuccessful())
                .andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), gson.toJson(expectedShotResult));
    }


}
