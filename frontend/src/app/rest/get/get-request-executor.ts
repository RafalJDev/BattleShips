import {Injectable} from "@angular/core";
import {RequestExecutor} from "../request-executor";

@Injectable()
export class GetRequestExecutor extends RequestExecutor {

  protected async get(url : string, options?) {
    let result = await this.httpClient
      .get<string>(url,options)
      .toPromise();
    return result;
  }

}
