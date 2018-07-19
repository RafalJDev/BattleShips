import {PostRequestExecutor} from "./post-request-executor";
import {Injectable} from "@angular/core";
import {PlayersService} from "../../services/players-service.service";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ShipSender extends PostRequestExecutor {

  hostUrl: string = PostRequestExecutor.getHostString();

  constructor(httpClient: HttpClient, private playersService: PlayersService) {
    super(httpClient);
  }

  postShip(shipArrayJson: string) {
    return this.post(this.hostUrl + "/ships?playerName=" + this.playersService.whoami.name, shipArrayJson);
  }

}
