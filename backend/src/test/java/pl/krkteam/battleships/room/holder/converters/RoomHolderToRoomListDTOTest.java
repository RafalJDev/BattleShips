package pl.krkteam.battleships.room.holder.converters;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.room.holder.dto.room.list.RoomDTO;
import pl.krkteam.battleships.room.holder.dto.room.list.RoomListDTO;

import static org.testng.AssertJUnit.assertEquals;

public class RoomHolderToRoomListDTOTest {

    RoomHolderToRoomListDTO converter;

    @BeforeMethod
    void setUp() {
        converter = new RoomHolderToRoomListDTO();
    }

    @Test
    void testConvertAndExpectConvertWell() {
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

    @Test
    void testConvertAndExpectGetOnlyFreeRooms() {
        RoomHolder roomHolder = new RoomHolder();
        String roomNameA = "RoomA";
        String roomNameB = "RoomB";

        RoomListDTO expectedRoomList = new RoomListDTO(
                new RoomDTO[]{new RoomDTO(roomNameB)});

        Player playerA = new Player("PlayerA");
        Player playerB = new Player("PlayerB");
        Player playerC = new Player("PlayerC");

        roomHolder.createRoomAndJoinPlayer(playerA, roomNameA);
        roomHolder.createRoomAndJoinPlayer(playerB, roomNameB);
        roomHolder.joinPlayer(roomNameA, playerC);

        assertEquals(expectedRoomList.toString(), converter.convert(roomHolder).toString());
    }

    @Test
    void testConvertAndExpectTwoFreeRooms() {
        RoomHolder roomHolder = new RoomHolder();
        String roomNameA = "RoomA";
        String roomNameB = "RoomB";
        String roomNameC = "RoomC";
        String roomNameD = "RoomD";

        RoomListDTO expectedRoomList = new RoomListDTO(
                new RoomDTO[]{new RoomDTO(roomNameB), new RoomDTO(roomNameC)});

        Player playerA = new Player("PlayerA");
        Player playerB = new Player("PlayerB");
        Player playerC = new Player("PlayerC");
        Player playerD = new Player("PlayerD");
        Player playerE = new Player("PlayerE");
        Player playerF = new Player("PlayerF");

        roomHolder.createRoomAndJoinPlayer(playerA, roomNameA);
        roomHolder.joinPlayer(roomNameA, playerC);
        roomHolder.createRoomAndJoinPlayer(playerB, roomNameB);
        roomHolder.createRoomAndJoinPlayer(playerD, roomNameC);
        roomHolder.createRoomAndJoinPlayer(playerE, roomNameD);
        roomHolder.joinPlayer(roomNameD, playerF);

        assertEquals(expectedRoomList.toString(), converter.convert(roomHolder).toString());
    }

    @Test
    void testConvertForNullAndExpectReturnNull() {
        RoomHolder roomHolder = null;
        assertEquals(converter.convert(roomHolder), null);
    }

}