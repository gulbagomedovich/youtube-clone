import { Component, OnInit } from '@angular/core';
import { VideoDto } from "../../dtos/VideoDto";
import { UserService } from "../../services/user.service";
import { VideoService } from "../../services/video.service";

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit {
  videos: Array<VideoDto> = [];

  constructor(private userService: UserService,
              private videoService: VideoService) { }

  ngOnInit(): void {
    const userId = localStorage.getItem("userId");
    if (userId) {
      this.userService.getSubscriptions(userId).subscribe(subscriptions => {
        this.videoService.getVideosBySubscriptions(subscriptions).subscribe(videos => {
          this.videos = videos;
        })
      });
    }
  }

}
