import {RequestExecutor} from "../request-executor";

export class PostRequestExecutor extends RequestExecutor {
  
  protected async post(url: string, data: string, options?) {
    let result = await this.httpClient
                           .post<string>(url, data, options)
                           .toPromise();
    return result;
  }
  
}
