export interface UpdateVideoRequest {
  videoTitle: string;
  videoDescription?: string;
  videoTags: Array<string>;
  videoStatus: string;
}
