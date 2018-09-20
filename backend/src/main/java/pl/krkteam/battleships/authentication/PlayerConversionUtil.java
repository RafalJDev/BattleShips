package pl.krkteam.battleships.authentication;

import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.PlayerDTO;

import java.util.Collection;

class PlayerConversionUtil {

    static Player convertPlayerDTOtoPlayer(PlayerDTO playerDTO) {
        return new Player(playerDTO.getName());
    }

    static PlayerDTO[] convertPlayerToPlayerDTOs(Collection<Player> playerCollection) {
        return playerCollection.stream()
                .map(playerDAO -> new PlayerDTO(playerDAO.getPlayerName()))
                .toArray(PlayerDTO[]::new);
    }

}
