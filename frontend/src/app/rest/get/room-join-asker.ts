import {HttpClient} from "@angular/common/http"
import {GetRequestExecutor} from "./get-request-executor"
import {PlayersService} from "../../services/player-identification/players-service.service"
import {RoomsService} from "../../services/player-identification/rooms-service"
import {Injectable} from "@angular/core"

@Injectable()
export class RoomJoinAsker extends GetRequestExecutor {
  
  hostUrl: string = GetRequestExecutor.getHostString()
  
  constructor(httpClient: HttpClient, private playersService: PlayersService, private roomsService: RoomsService) {
    super(httpClient)
  }
  
  public getCanPlayerJoinRoom() {
    //todo REST API turn to round
    return this.get(this.hostUrl + "/room/join?playerName=" + this.playersService.whoami.name +
      "&roomName=" + this.roomsService.room.roomName)
    
  }
}
