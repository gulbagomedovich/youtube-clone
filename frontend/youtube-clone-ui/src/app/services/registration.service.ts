import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  url: string = "http://localhost:8080/api";

  constructor(private httpClient: HttpClient) { }

  signup(): Observable<string> {
    const path = "/signup";
    return this.httpClient.post(this.url.concat(path), null,
      {
        responseType: "text"
      });
  }

  checkRegistration(): Observable<boolean> {
    const path = "/check-registration";
    return this.httpClient.get<boolean>(this.url.concat(path));
  }
}
