import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { VideoService } from "../../services/video.service";
import { UserService } from "../../services/user.service";

@Component({
  selector: 'app-video-metadata',
  templateUrl: './video-metadata.component.html',
  styleUrls: ['./video-metadata.component.css']
})
export class VideoMetadataComponent implements OnInit {
  id!: string;
  title!: string;
  description!: string;
  likesCount!: number;
  dislikesCount!: number;
  tags!: Array<string>;
  videoUrl!: string;
  status!: string;
  viewsCount!: number;
  videoUploadDate!: string;

  userId!: string;
  firstName!: string;
  lastName!: string;
  picture!: string;
  subscribersCount!: number;

  currentUserId: string | null = localStorage.getItem("userId");
  isSubscribed!: boolean;

  constructor(private activatedRoute: ActivatedRoute, private router: Router,
              private videoService: VideoService, private userService: UserService) { }

  ngOnInit(): void {
    const videoId = this.activatedRoute.snapshot.params['videoId'];

    this.videoService.getVideo(videoId).subscribe(
      videoDto => {
        this.id = videoDto.id;
        this.title = videoDto.title;
        this.description = videoDto.description;
        this.likesCount = videoDto.likesCount;
        this.dislikesCount = videoDto.dislikesCount;
        this.tags = videoDto.tags;
        this.videoUrl = videoDto.videoUrl;
        this.status = videoDto.status;
        this.viewsCount = videoDto.viewsCount;
        this.videoUploadDate = videoDto.videoUploadDate;

        this.userService.getUser(videoDto.userId).subscribe(userDto => {
          this.userId = userDto.id;
          this.firstName = userDto.firstName;
          this.lastName = userDto.lastName;
          this.picture = userDto.picture;
          this.subscribersCount = userDto.subscribers.length;

          this.userService.checkSubscription(userDto.id).subscribe(isSubscribed => {
            this.isSubscribed = isSubscribed;
          });
        });
      },
      error => {
        console.error("Test " + error);
        this.router.navigateByUrl("/");
      });
  }

  like() {
    if (!this.currentUserId) {
      console.log("Необходимо зарегистрироваться");
    }

    this.videoService.likeVideo(this.id).subscribe(videoDto => {
      this.likesCount = videoDto.likesCount;
      this.dislikesCount = videoDto.dislikesCount;
    });
  }

  dislike() {
    if (!this.currentUserId) {
      console.log("Необходимо зарегистрироваться");
    }

    this.videoService.dislikeVideo(this.id).subscribe(videoDto => {
      this.likesCount = videoDto.likesCount;
      this.dislikesCount = videoDto.dislikesCount;
    });
  }

  subscribe() {
    this.userService.subscribe(this.userId).subscribe(userDto => {
      this.isSubscribed = true;
      this.subscribersCount = userDto.subscribers.length;
    });
  }

  unsubscribe() {
    this.userService.unsubscribe(this.userId).subscribe(userDto => {
      this.isSubscribed = false;
      this.subscribersCount = userDto.subscribers.length;
    });
  }
}
