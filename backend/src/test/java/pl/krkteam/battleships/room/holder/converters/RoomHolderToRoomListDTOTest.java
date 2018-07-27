package pl.krkteam.battleships.room.holder.converters;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.room.holder.dto.room.list.RoomDTO;
import pl.krkteam.battleships.room.holder.dto.room.list.RoomListDTO;

import static org.testng.AssertJUnit.assertEquals;

public class RoomHolderToRoomListDTOTest {

    private RoomHolderToRoomListDTO converter;

    @Mock
    private Player player;

    @BeforeMethod
    public void setUp() {
        converter = new RoomHolderToRoomListDTO();
    }

    @Test
    public void testConvert() {
        RoomHolder roomHolder = new RoomHolder();
        String roomNameA = "RoomA";
        String roomNameB = "RoomB";

        RoomListDTO expectedRoomList = new RoomListDTO(
                new RoomDTO[]{new RoomDTO(roomNameA), new RoomDTO(roomNameB)});

        Player playerA = new Player("PlayerA");
        Player playerB = new Player("PlayerB");

        roomHolder.createRoomAndJoinPlayer(playerA, roomNameA);
        roomHolder.createRoomAndJoinPlayer(playerB, roomNameB);

        assertEquals(converter.convert(roomHolder).toString(), expectedRoomList.toString());
    }

}