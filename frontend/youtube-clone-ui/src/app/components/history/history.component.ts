import { Component, OnInit } from '@angular/core';
import { VideoDto } from "../../dtos/VideoDto";
import { VideoService } from "../../services/video.service";
import { UserService } from "../../services/user.service";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  videos: Array<VideoDto> = [];

  constructor(private userService: UserService,
              private videoService: VideoService) { }

  ngOnInit(): void {
    const userId = localStorage.getItem("userId");
    if (userId) {
      this.userService.getVideoHistory(userId).subscribe(ids => {
        this.videoService.castIdsToVideos(ids).subscribe(videos => {
          this.videos = videos;
        })
      });
    }
  }

}
