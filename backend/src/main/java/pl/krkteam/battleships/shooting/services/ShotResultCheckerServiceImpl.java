package pl.krkteam.battleships.shooting.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.shooting.conventers.CoordinateDTOtoCoordinates;
import pl.krkteam.battleships.shooting.dto.ShotDTO;
import pl.krkteam.battleships.shooting.dto.result.ResultWrongShotDTO;
import pl.krkteam.battleships.shooting.dto.result.ShotResultDTO;

@Service
public class ShotResultCheckerServiceImpl implements ShotResultCheckerService {

    private static final Logger logger = LogManager.getLogger(ShotResultCheckerServiceImpl.class);

    private final CoordinateDTOtoCoordinates coordinateDTOtoCoordinates;
    
    public ShotResultCheckerServiceImpl(
            CoordinateDTOtoCoordinates coordinateDTOtoCoordinates) {
        this.coordinateDTOtoCoordinates = coordinateDTOtoCoordinates;
    }

    @Override
    public ShotResultDTO checkShotResult(ShotDTO shotDTO, GameBoard gameBoard) {

        Coordinates shotCoor = coordinateDTOtoCoordinates.convert(shotDTO.getShotCoordinate());
        final Board board = gameBoard.getBoard();

        ChainOfShotResult chainOfShotResult = new CheckMiss();
        try {
            return chainOfShotResult.getShotResult(shotCoor, board);
        } catch (UnexpectedShotResultException e) {
            logger.info("Exception message: :" + e.getMessage());
            return new ResultWrongShotDTO();
        }
    }

}
