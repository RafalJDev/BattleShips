import {GetRequestExecutor} from "./get-request-executor"
import {PlayersService} from "../../services/player-identification/players-service.service"
import {HttpClient} from "@angular/common/http"
import {Injectable} from "@angular/core"
import {RoomsService} from "../../services/player-identification/rooms-service"

@Injectable()
export class FirstRoundAsker extends GetRequestExecutor {
  
  hostUrl: string = GetRequestExecutor.getHostString()
  
  constructor(httpClient: HttpClient, private playersService: PlayersService, private roomsService: RoomsService) {
    super(httpClient)
  }
  
  public getFirstRoundResult() {
    //todo REST API turn to round
    return this.get(this.hostUrl + "/game/player/isTurn?playerName=" + this.playersService.whoami.name +
      "&roomName=" + this.roomsService.room.roomName)
  }
}
