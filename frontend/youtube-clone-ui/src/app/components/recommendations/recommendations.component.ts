import { Component, OnInit } from '@angular/core';
import { VideoService } from "../../services/video.service";
import { VideoDto } from "../../dtos/VideoDto";

@Component({
  selector: 'app-recommendations',
  templateUrl: './recommendations.component.html',
  styleUrls: ['./recommendations.component.css']
})
export class RecommendationsComponent implements OnInit {
  videos: Array<VideoDto> = [];

  constructor(private videoService: VideoService) { }

  ngOnInit(): void {
    this.videoService.getVideos().subscribe(videos => {
      this.videos = videos;
    });
  }

}
