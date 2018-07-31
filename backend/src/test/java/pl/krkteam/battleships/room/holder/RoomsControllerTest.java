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
import pl.krkteam.battleships.room.holder.dto.room.list.RoomListDTO;

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
        RoomDTO roomA = new RoomDTO("roomA");
        RoomDTO roomB = new RoomDTO("roomB");
        RoomDTO roomC = new RoomDTO("roomC");
        RoomListDTO roomListDTO = new RoomListDTO(new RoomDTO[]{roomA, roomB, roomC});

        when(roomHolderToRoomListDTO.convert(any())).thenReturn(roomListDTO);

        mockMvc.perform(get("/room/list"))
                .andExpect(status()
                        .is2xxSuccessful());

        verify(roomHolderToRoomListDTO, times(1)).convert(any());
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

        when(roomHolder.createRoomAndJoinPlayer(any(), eq("SomeRoom")))
                .thenReturn(new CreateRoomOkDTO());

        mockMvc.perform(post("/room/create")
                .content(roomJson)
                .param("playerName", playerName))
                .andExpect(status()
                        .is2xxSuccessful());

        verify(roomHolder, times(1)).createRoomAndJoinPlayer(any(), anyString());
    }

}
