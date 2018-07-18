import {PostRequestExecutor} from "./post-request-executor";
import {Injectable} from "@angular/core";

@Injectable()
export class LoginRequestSender extends PostRequestExecutor {

  hostUrl: string = PostRequestExecutor.getHostString();

  postLogin(authData: string) {
    return this.post(this.hostUrl + "/login",authData);
  }

}
