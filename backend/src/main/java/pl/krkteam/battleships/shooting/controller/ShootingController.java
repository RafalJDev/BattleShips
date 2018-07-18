package pl.krkteam.battleships.shooting.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.battleships.shooting.controller.mocking.as.hell.MockedBoard;
import pl.krkteam.battleships.shooting.models.dto.ShotDTO;
import pl.krkteam.battleships.shooting.models.dto.result.ResultHitDTO;
import pl.krkteam.battleships.shooting.models.dto.result.ResultMissDTO;
import pl.krkteam.battleships.shooting.models.dto.result.ResultSunkDTO;
import pl.krkteam.battleships.shooting.models.dto.result.ShotResultDTO;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ShootingController {

  MockedBoard mockedBoard;

  public ShootingController(MockedBoard mockedBoard) {
    this.mockedBoard = mockedBoard;
  }

  @PostMapping(value = "/game/player/shot")
  public String communicateWithAngularByPostingShip(@RequestBody String post) {

    Gson gson = new Gson();
    ShotDTO shotDTO = gson.fromJson(post, ShotDTO.class);
    System.out.println("Shot from frontend:" + shotDTO);

    ShotResultDTO shotResultDTO = shotResponse(shotDTO);

    String shotResultJson = gson.toJson(shotResultDTO);
    System.out.println(shotResultJson);

    return shotResultJson;
  }

  public ShotResultDTO shotResponse(ShotDTO shotDTO) {
    ShotResultDTO shotResultDTO = new ResultMissDTO();

    if (mockedBoard.isThereSuchMastOnFirstShip(shotDTO.getShotCoordinate())) {
      shotResultDTO = new ResultHitDTO();
      mockedBoard.hittedMastCountForFirstShip++;
    }

    if (mockedBoard.isThereSuchMastOnSecondShip(shotDTO.getShotCoordinate())) {
      shotResultDTO = new ResultHitDTO();
      mockedBoard.hittedMastCountForSecondShip++;
    }

    System.out.println("hittedMastCountForFirstShip" + mockedBoard.hittedMastCountForFirstShip);
    if (mockedBoard.hittedMastCountForFirstShip == mockedBoard.shipDTOS[0].getCoordinates().length) {
      shotResultDTO = new ResultSunkDTO();
      mockedBoard.hittedMastCountForFirstShip = 0;
    }

    System.out.println("hittedMastCountForSecondShip" + mockedBoard.hittedMastCountForSecondShip);

    if (mockedBoard.hittedMastCountForSecondShip == mockedBoard.shipDTOS[1].getCoordinates().length) {
      shotResultDTO = new ResultSunkDTO();
      mockedBoard.hittedMastCountForSecondShip = 0;
    }

    return shotResultDTO;
  }
}
