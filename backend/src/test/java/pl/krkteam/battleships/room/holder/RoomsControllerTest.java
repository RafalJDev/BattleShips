package pl.krkteam.battleships.room.holder;

import com.google.gson.Gson;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.room.holder.converters.RoomHolderToRoomListDTO;
import pl.krkteam.battleships.room.holder.dto.create.room.CreateRoomOkDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultOkDTO;
import pl.krkteam.battleships.room.holder.dto.room.list.RoomDTO;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoomsControllerTest {

    private RoomsController roomsController;

    @Mock
    private RoomHolder roomHolder;

    @Mock
    private RoomHolderToRoomListDTO roomHolderToRoomListDTO;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        roomsController = new RoomsController(roomHolder, roomHolderToRoomListDTO);

        mockMvc = MockMvcBuilders.standaloneSetup(roomsController).build();
    }


    @Test
    public void testGetRoomList() throws Exception {
        Room roomA = new Room("roomA");
        Room roomB = new Room("roomB");
        Room roomC = new Room("roomC");
        List<Room> roomList = Arrays.asList(roomA, roomB, roomC);

        when(roomHolder.getRoomList()).thenReturn(roomList);

        mockMvc.perform(get("/room/list"))
                .andExpect(status()
                        .is2xxSuccessful());

        verify(roomHolder, times(0)).getRoomList();
    }

    @Test
    public void joinExistingRoomAndExpectJoined() throws Exception {
        String roomName = "SomeRoom";
        String joiningPlayer = "JoiningPlayer";

        when(roomHolder.joinPlayer(eq(roomName), any()))
                .thenReturn(new JoinResultOkDTO());

        mockMvc.perform(get("/room/join")
                .param("playerName", joiningPlayer)
                .param("roomName", roomName))
                .andExpect(status()
                        .is2xxSuccessful());

        verify(roomHolder, times(1)).joinPlayer(anyString(), any());
    }


    @Test
    public void testCreateRoom() throws Exception {
        String playerName = "SomePlayer";

        RoomDTO roomDTO = new RoomDTO("SomeRoom");
        Gson gson = new Gson();
        final String roomJson = gson.toJson(roomDTO);

        when(roomHolder.createRoom(any(), eq("SomeRoom")))
                .thenReturn(new CreateRoomOkDTO());

        mockMvc.perform(post("/room/create")
                .content(roomJson)
                .param("playerName", playerName))
                .andExpect(status()
                        .is2xxSuccessful());

        verify(roomHolder, times(1)).createRoom(any(), anyString());
    }

}