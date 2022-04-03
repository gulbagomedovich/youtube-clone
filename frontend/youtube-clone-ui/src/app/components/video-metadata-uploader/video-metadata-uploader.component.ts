import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";
import { VideoService } from "../../services/video.service";
import { MatChipInputEvent } from "@angular/material/chips";
import { Router } from "@angular/router";
import { AddVideoResponse } from "../../dtos/AddVideoResponse";
import { UpdateVideoRequest } from "../../dtos/UpdateVideoRequest";

@Component({
  selector: 'app-video-metadata-uploader',
  templateUrl: './video-metadata-uploader.component.html',
  styleUrls: ['./video-metadata-uploader.component.css']
})
export class VideoMetadataUploaderComponent implements OnInit {
  previewFile: File | undefined;

  titleFormControl: FormControl = new FormControl();
  descriptionFormControl: FormControl = new FormControl();
  statusFormControl: FormControl = new FormControl();
  formGroup: FormGroup = new FormGroup({
    title: this.titleFormControl,
    description: this.descriptionFormControl,
    status: this.statusFormControl
  });

  tags: string[] = [];

  videoId!: string;
  videoTitle!: string;
  videoUrl!: string;
  videoStatus!: string;

  constructor(private videoService: VideoService, private router: Router) { }

  ngOnInit(): void {
    const addVideoResponse: AddVideoResponse = history.state.addVideoResponse;

    if (!addVideoResponse) {
      this.router.navigateByUrl("/");
    }

    this.videoId = addVideoResponse.videoId
    this.videoTitle = addVideoResponse.videoTitle
    this.videoUrl = addVideoResponse.videoUrl;
    this.videoStatus = addVideoResponse.videoStatus;

    this.statusFormControl.setValue(this.videoStatus);
  }

  addTag(event: MatChipInputEvent) {
    const value = event.value;
    if (value.trim() !== "") {
      this.tags.push(value.trim());
    }

    const input = event.chipInput;
    if (input) {
      input.clear()
    }
  }

  removeTag(tag: string) {
    const index = this.tags.indexOf(tag);
    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }

  onFileSelected(event: any) {
    this.previewFile = event.target.files[0];
  }

  uploadVideoMetadata() {
    if (this.formGroup.valid) {
      const updateVideoRequest: UpdateVideoRequest = {
        "videoTitle": this.formGroup.get("title")?.value,
        "videoDescription": this.formGroup.get("description")?.value,
        "videoTags": this.tags,
        "videoStatus": this.formGroup.get("status")?.value
      };
      this.videoService.updateVideo(this.videoId, updateVideoRequest).subscribe(() => {
        if (this.previewFile) {
          this.videoService.updatePreview(this.videoId, this.previewFile).subscribe();
        }

        this.router.navigateByUrl("/" + this.videoId);
      });
    }
  }
}
