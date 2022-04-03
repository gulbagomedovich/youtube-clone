export interface VideoDto {
  id: string;
  title: string;
  description: string;
  userId: string;
  likesCount: number;
  dislikesCount: number;
  tags: Array<string>;
  videoUrl: string;
  status: string;
  viewsCount: number;
  previewUrl: string;
  videoUploadDate: string;
}
