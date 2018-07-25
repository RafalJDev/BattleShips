package pl.krkteam.battleships.ships.placing.validation;

import com.google.gson.Gson;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.domain.GameBoardHolder;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.CoordinateDTO;
import pl.krkteam.battleships.common.dto.PlacingValidationResultDTO;
import pl.krkteam.battleships.common.dto.ShipDTO;
import pl.krkteam.battleships.common.dto.ShipHolderDTO;
import pl.krkteam.battleships.ships.placing.validation.converters.ShipsToShipHolder;
import pl.krkteam.battleships.ships.placing.validation.fromJson.CoordinatesFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipFromJson;
import pl.krkteam.battleships.ships.placing.validation.fromJson.ShipHolderFromJson;
import pl.krkteam.battleships.ships.placing.validation.services.ShipsPlacingValidatorService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShipControllerTest {

    ShipController shipController;

    @Mock
    ShipsPlacingValidatorService shipsLocationValidatorService;

    @Mock
    ShipsToShipHolder shipsToShipHolder;

    @Mock
    Game game;

    MockMvc mockMvc;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        shipController = new ShipController(
                shipsLocationValidatorService, shipsToShipHolder, game);

        mockMvc = MockMvcBuilders.standaloneSetup(shipController).build();

    }

    @Test
    public void testValidateAndSaveShips() throws Exception {
        ShipDTO shipDTOA = new ShipDTO(new CoordinateDTO[]{
                new CoordinateDTO(2, 3),
                new CoordinateDTO(2, 4),
                new CoordinateDTO(2, 5)
        });
        ShipDTO shipDTOB = new ShipDTO(new CoordinateDTO[]{
                new CoordinateDTO(1, 8),
                new CoordinateDTO(2, 8),
                new CoordinateDTO(3, 8)
        });
        ShipHolderDTO shipHolderDTO = new ShipHolderDTO(new ShipDTO[]{shipDTOA, shipDTOB});

        ShipHolderFromJson shipHolderFromJson = new ShipHolderFromJson();

        ShipFromJson shipFromJsonA = new ShipFromJson(new CoordinatesFromJson[]{
                new CoordinatesFromJson(0, 2),
                new CoordinatesFromJson(1, 2),
                new CoordinatesFromJson(2, 2),
                new CoordinatesFromJson(3, 2)
        });
        ShipFromJson shipFromJsonB = new ShipFromJson(new CoordinatesFromJson[]{
                new CoordinatesFromJson(2, 0),
                new CoordinatesFromJson(2, 1),
                new CoordinatesFromJson(2, 2)
        });
        shipHolderFromJson.addShipFromJson(shipFromJsonA);
        shipHolderFromJson.addShipFromJson(shipFromJsonB);


        when(shipsToShipHolder.convert(any())).thenReturn(shipHolderFromJson);
        when(shipsLocationValidatorService.validateShipLocation(any(), any())).thenReturn(
                new PlacingValidationResultDTO(PlacingValidationResultDTO.Result.OK));

        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        Player player = new Player("SomePlayer");
        gameBoardHolder.addPlayer(player, new GameBoard());
        when(game.getGameBoardHolder()).thenReturn(gameBoardHolder);


        Gson gson = new Gson();
        final String shipsJson = gson.toJson(shipHolderDTO);

        mockMvc.perform(post("/ships")
                .content(shipsJson)
                .param("playerName", "SomePlayer"))
                .andExpect(status()
                        .is2xxSuccessful());
    }

}
