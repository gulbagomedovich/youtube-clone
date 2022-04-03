import { Component, OnInit } from '@angular/core';
import { RegistrationService } from "./services/registration.service";
import { OidcSecurityService } from "angular-auth-oidc-client";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'youtube-clone-ui';

  constructor(private registrationService: RegistrationService,
              private oidcSecurityService: OidcSecurityService) { }

  ngOnInit(): void {
    setTimeout(() => {
      if (this.oidcSecurityService.isAuthenticated()) {
        this.registrationService.checkRegistration().subscribe(isRegistered => {
          if (!isRegistered) {
            this.registrationService.signup().subscribe(userId => {
              localStorage.setItem("userId", userId);
            });
          }
        });
      }
    }, 1000);
  }
}
