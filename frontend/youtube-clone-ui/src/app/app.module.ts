import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { VideoUploaderComponent } from './components/video-uploader/video-uploader.component';
import { NgxFileDropModule } from "ngx-file-drop";
import { VideoMetadataUploaderComponent } from './components/video-metadata-uploader/video-metadata-uploader.component';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatChipsModule } from "@angular/material/chips";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { FlexModule } from "@angular/flex-layout";
import { VideoPlayerComponent } from './components/video-player/video-player.component';
import { VgCoreModule } from "@videogular/ngx-videogular/core";
import { MatRadioModule } from "@angular/material/radio";
import { MatCardModule } from "@angular/material/card";
import { VideoMetadataComponent } from './components/video-metadata/video-metadata.component';
import { MatListModule } from "@angular/material/list";
import { DragDropModule } from "@angular/cdk/drag-drop";
import { AuthConfigModule } from './auth/auth-config.module';
import { AuthInterceptor } from "angular-auth-oidc-client";
import { HomeComponent } from './components/home/home.component';
import { HistoryComponent } from './components/history/history.component';
import { SubscriptionsComponent } from './components/subscriptions/subscriptions.component';
import { LikedVideosComponent } from './components/liked-videos/liked-videos.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { MatSidenavModule } from "@angular/material/sidenav";
import { RecommendationsComponent } from './components/recommendations/recommendations.component';
import { VideoCardComponent } from './components/video-card/video-card.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    VideoUploaderComponent,
    VideoMetadataUploaderComponent,
    VideoPlayerComponent,
    VideoMetadataComponent,
    HomeComponent,
    HistoryComponent,
    SubscriptionsComponent,
    LikedVideosComponent,
    SidebarComponent,
    RecommendationsComponent,
    VideoCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    NgxFileDropModule,
    MatFormFieldModule,
    MatInputModule,
    MatChipsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FlexModule,
    FormsModule,
    VgCoreModule,
    MatRadioModule,
    MatCardModule,
    MatListModule,
    DragDropModule,
    AuthConfigModule,
    MatSidenavModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
