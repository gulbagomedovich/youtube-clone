import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { OidcSecurityService } from "angular-auth-oidc-client";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isAuthenticated: boolean = false;

  constructor(private router: Router, private oidcSecurityService: OidcSecurityService) { }

  ngOnInit(): void {
    this.oidcSecurityService.checkAuth().subscribe(({ isAuthenticated }) => {
      this.isAuthenticated = isAuthenticated;
    });
  }

  addVideo() {
    this.router.navigateByUrl("/upload-video");
  }

  login() {
    this.oidcSecurityService.authorize();
  }
}
