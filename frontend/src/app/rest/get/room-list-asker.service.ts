import {Injectable} from '@angular/core'
import {GetRequestExecutor} from "./get-request-executor"
import {PostRequestExecutor} from "../post/post-request-executor"
import {HttpClient} from "@angular/common/http"

@Injectable()
export class RoomListAsker extends GetRequestExecutor {
  
  hostUrl: string = PostRequestExecutor.getHostString()
  
  constructor(httpClient: HttpClient) {
    super(httpClient)
  }
  
  getRoomList() {
    return this.get(this.hostUrl + "/room/list")
  }
  
}
