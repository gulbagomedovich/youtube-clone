import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { VideoDto } from "../dtos/VideoDto";
import { AddVideoResponse } from "../dtos/AddVideoResponse";
import { UpdateVideoRequest } from "../dtos/UpdateVideoRequest";

@Injectable({
  providedIn: 'root'
})
export class VideoService {
  url: string = "http://localhost:8080/api/videos";

  constructor(private httpClient: HttpClient) { }

  addVideo(videoFile: File): Observable<AddVideoResponse> {
    const formData = new FormData();
    formData.append('video', videoFile, videoFile.name);

    const path = "/";
    return this.httpClient.post<AddVideoResponse>(this.url.concat(path), formData);
  }

  getVideos(): Observable<Array<VideoDto>> {
    const path = "/";
    return this.httpClient.get<Array<VideoDto>>(this.url.concat(path));
  }

  getVideo(id: string): Observable<VideoDto> {
    const path = "/".concat(id);
    return this.httpClient.get<VideoDto>(this.url.concat(path));
  }

  updateVideo(id: string, updateVideoRequest: UpdateVideoRequest): Observable<any> {
    const path = "/".concat(id);
    return this.httpClient.patch(this.url.concat(path), updateVideoRequest);
  }

  updatePreview(videoId: string, previewFile: File): Observable<any> {
    const formData = new FormData();
    formData.append('videoId', videoId);
    formData.append('preview', previewFile, previewFile.name);

    const path = "/";
    return this.httpClient.patch(this.url.concat(path), formData);
  }

  likeVideo(videoId: string): Observable<VideoDto> {
    const path = "/" + videoId + "/like";
    return this.httpClient.post<VideoDto>(this.url.concat(path), null);
  }

  dislikeVideo(videoId: string): Observable<VideoDto> {
    const path = "/" + videoId + "/dislike";
    return this.httpClient.post<VideoDto>(this.url.concat(path), null);
  }

  castIdsToVideos(ids: Array<string>): Observable<Array<VideoDto>> {
    const path = "/cast-ids-to-videos";
    return this.httpClient.post<Array<VideoDto>>(this.url.concat(path), ids);
  }

  getVideosBySubscriptions(subscriptions: Array<string>): Observable<Array<VideoDto>> {
    const path = "/by-subscriptions";
    return this.httpClient.post<Array<VideoDto>>(this.url.concat(path), subscriptions);
  }
}
