import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VideoUploaderComponent } from "./components/video-uploader/video-uploader.component";
import { VideoMetadataUploaderComponent } from "./components/video-metadata-uploader/video-metadata-uploader.component";
import { VideoMetadataComponent } from "./components/video-metadata/video-metadata.component";
import { HomeComponent } from "./components/home/home.component";
import { SubscriptionsComponent } from "./components/subscriptions/subscriptions.component";
import { HistoryComponent } from "./components/history/history.component";
import { LikedVideosComponent } from "./components/liked-videos/liked-videos.component";
import { RecommendationsComponent } from "./components/recommendations/recommendations.component";

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
    children: [
      {
        path: "recommendations",
        component: RecommendationsComponent
      },
      {
        path: "subscriptions",
        component: SubscriptionsComponent
      },
      {
        path: "history",
        component: HistoryComponent
      },
      {
        path: "liked-videos",
        component: LikedVideosComponent
      }
    ]
  },
  {
    path: "upload-video",
    component: VideoUploaderComponent
  },
  {
    path: "upload-video-metadata",
    component: VideoMetadataUploaderComponent
  },
  {
    path: ":videoId",
    component: VideoMetadataComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
