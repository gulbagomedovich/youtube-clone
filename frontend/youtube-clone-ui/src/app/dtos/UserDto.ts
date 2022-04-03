export interface UserDto {
  id: string;
  sub: string;
  firstName: string;
  lastName: string;
  picture: string;
  email: string;
  subscriptions: Array<string>;
  subscribers: Array<string>;
  videoHistory: Array<string>;
  likedVideos: Array<string>;
  dislikedVideos: Array<string>;
}
