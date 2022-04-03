import { Component, Input, OnInit } from '@angular/core';
import { VideoDto } from "../../dtos/VideoDto";
import { UserService } from "../../services/user.service";

@Component({
  selector: 'app-video-card',
  templateUrl: './video-card.component.html',
  styleUrls: ['./video-card.component.css']
})
export class VideoCardComponent implements OnInit {
  @Input()
  video!: VideoDto;

  firstname!: string;
  lastname!: string;
  picture!: string;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUser(this.video.userId).subscribe(userDto => {
      this.firstname = userDto.firstName;
      this.lastname = userDto.lastName;
      this.picture = userDto.picture;
    });
  }

}
