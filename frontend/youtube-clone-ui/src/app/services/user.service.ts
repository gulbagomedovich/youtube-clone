import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { UserDto } from "../dtos/UserDto";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url: string = "http://localhost:8080/api/users";

  constructor(private httpClient: HttpClient) { }

  getUser(id: string): Observable<UserDto> {
    const path = "/".concat(id);
    return this.httpClient.get<UserDto>(this.url.concat(path));
  }

  checkSubscription(userId: string): Observable<boolean> {
    const path = "/" + userId + "/check-subscription";
    return this.httpClient.get<boolean>(this.url.concat(path));
  }

  subscribe(userId: string): Observable<UserDto> {
    const path = "/" + userId + "/subscribe";
    return this.httpClient.post<UserDto>(this.url.concat(path), null);
  }

  unsubscribe(userId: string): Observable<UserDto> {
    const path = "/" + userId + "/unsubscribe";
    return this.httpClient.post<UserDto>(this.url.concat(path), null);
  }

  getSubscriptions(userId: string): Observable<Array<string>> {
    const path = "/" + userId + "/subscriptions";
    return this.httpClient.get<Array<string>>(this.url.concat(path));
  }

  getVideoHistory(userId: string): Observable<Array<string>> {
    const path = "/" + userId + "/video-history";
    return this.httpClient.get<Array<string>>(this.url.concat(path));
  }

  getLikedVideos(userId: string): Observable<Array<string>> {
    const path = "/" + userId + "/liked-videos";
    return this.httpClient.get<Array<string>>(this.url.concat(path));
  }
}
