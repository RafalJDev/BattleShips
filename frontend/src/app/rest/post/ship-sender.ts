import {PostRequestExecutor} from "./post-request-executor";
import {Injectable} from "@angular/core";

@Injectable()
export class ShipSender extends PostRequestExecutor {

  hostUrl: string = PostRequestExecutor.getHostString();

  postShip(shipArrayJson: string) {
    return this.post(this.hostUrl + "/ships", shipArrayJson);
  }

}
