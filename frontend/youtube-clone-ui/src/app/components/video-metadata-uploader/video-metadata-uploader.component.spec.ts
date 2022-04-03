import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoMetadataUploaderComponent } from './video-metadata-uploader.component';

describe('VideoMetadataUploaderComponent', () => {
  let component: VideoMetadataUploaderComponent;
  let fixture: ComponentFixture<VideoMetadataUploaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VideoMetadataUploaderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VideoMetadataUploaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
