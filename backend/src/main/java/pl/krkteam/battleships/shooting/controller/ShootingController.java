package pl.krkteam.battleships.shooting.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.domain.GameBoardHolder;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.result.models.dto.*;
import pl.krkteam.battleships.shooting.controller.mocking.as.hell.MockedBoard;
import pl.krkteam.battleships.shooting.models.dto.ShotDTO;
import pl.krkteam.battleships.shooting.models.dto.result.*;
import pl.krkteam.battleships.shooting.services.ShotResultCheckerService;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ShootingController {
  
  MockedBoard mockedBoard;
  private final ShotResultCheckerService shotResultCheckerService;


  public ShootingController(ShotResultCheckerService shotResultCheckerService) {
    this.shotResultCheckerService = shotResultCheckerService;
  }

  @Autowired
  Game game;

  @PostMapping(value = "/game/player/shot")
  public String communicateWithAngularByPostingShip(@RequestBody String post, @RequestParam String playerName) {
    System.out.println(post);

    Player player = new Player(playerName);

    Gson gson = new Gson();
    ShotDTO shotDTO = gson.fromJson(post, ShotDTO.class);
    System.out.println("Shot from frontend:" + shotDTO);

    Player opponent = game.shotResultQueueHolder.getOpponent(player);

    final GameBoard opponentGameBoard = game.gameBoardHolder.getGameBoard(opponent);
    final ShotResultDTO shotResultDTO = shotResultCheckerService.checkShotResult(shotDTO,opponentGameBoard);

    OpponentShotResult opponentShotResult = new OpponentNoShot();

    int x = shotDTO.getShotCoordinate().getX();
    int y = shotDTO.getShotCoordinate().getY();

    if (shotResultDTO instanceof ResultHitDTO) {
      opponentShotResult = new OpponentShotHit(x, y);
    }
    if (shotResultDTO instanceof ResultMissDTO) {
      opponentShotResult = new OpponentShotMiss(x, y);
    }
    if (shotResultDTO instanceof ResultSunkDTO) {
      opponentShotResult = new OpponentShotSunk(x, y);
    }
    if (shotResultDTO instanceof ResultPlayerWonDTO) {
      opponentShotResult = new OpponentShotPlayerLoose(x, y);
    }

    game.shotResultQueueHolder.getShotResultQueue(opponent).addShotResult(opponentShotResult);

    String shotResultJson = gson.toJson(shotResultDTO);
    System.out.println(shotResultJson);

    return shotResultJson;
  }
  
  //this method is mocked shot verifier
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
