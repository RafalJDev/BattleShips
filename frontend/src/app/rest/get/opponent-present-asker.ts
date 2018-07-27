import {RoomsService} from "../../services/player-identification/rooms-service"
import {HttpClient, HttpEvent} from "@angular/common/http"
import {GetRequestExecutor} from "./get-request-executor"
import {PlayersService} from "../../services/player-identification/players-service.service"
import {Injectable} from "@angular/core"

@Injectable()
export class OpponentPresentAsker extends GetRequestExecutor {
  
  hostUrl: string = GetRequestExecutor.getHostString()
  
  constructor(httpClient: HttpClient, private playersService: PlayersService, private roomsService: RoomsService) {
    super(httpClient)
  }
  
  public getOpponenIsPresent() {
    //todo 27.7 REST API turn to round
    return this.get(this.hostUrl + "/room/opponent/present?playerName=" + this.playersService.whoami.name +
      "&roomName=" + this.roomsService.room.roomName)
  }
  
  responseToBoolean(opponentPresentResponse: HttpEvent<string>): boolean {
    let opponentPresentString = opponentPresentResponse['response']
    
    switch (opponentPresentString) {
      case "OpponentPresent":
        return true
      case "OpponentAbsent":
        return false
    }
  }
}
