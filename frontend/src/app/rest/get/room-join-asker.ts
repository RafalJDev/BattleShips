import {HttpClient, HttpEvent} from "@angular/common/http"
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
    //todo 27.7 REST API turn to round
    return this.get(this.hostUrl + "/room/join?playerName=" + this.playersService.whoami.name +
      "&roomName=" + this.roomsService.room.roomName)
  }
  
  responseToBoolean(joinResponse: HttpEvent<string>): boolean {
    let joinResponseString = joinResponse['result']
    
    switch (joinResponseString) {
      case "Ok":
        return true
      case "Wrong":
        return false
    }
  }
  
}
