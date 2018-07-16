import {Injectable} from "@angular/core";
import {PostRequestExecutor} from "./post-request-executor";

@Injectable()
export class ShotSender extends PostRequestExecutor {
  
  hostUrl: string = PostRequestExecutor.getHostString();
  
  postShot(shotJson: string) {
    return this.post(this.hostUrl + "/shot", shotJson);
  }
  
}
