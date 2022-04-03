import { Component, OnInit } from '@angular/core';
import { VideoDto } from "../../dtos/VideoDto";
import { UserService } from "../../services/user.service";
import { VideoService } from "../../services/video.service";

@Component({
  selector: 'app-liked-videos',
  templateUrl: './liked-videos.component.html',
  styleUrls: ['./liked-videos.component.css']
})
export class LikedVideosComponent implements OnInit {
  videos: Array<VideoDto> = [];

  constructor(private userService: UserService,
              private videoService: VideoService) { }

  ngOnInit(): void {
    const userId = localStorage.getItem("userId");
    if (userId) {
      this.userService.getLikedVideos(userId).subscribe(ids => {
        this.videoService.castIdsToVideos(ids).subscribe(videos => {
          this.videos = videos;
        })
      });
    }
  }

}
