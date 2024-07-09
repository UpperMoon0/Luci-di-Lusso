import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostJewelryComponent } from './post-jewelry.component';

describe('PostJewelryComponent', () => {
  let component: PostJewelryComponent;
  let fixture: ComponentFixture<PostJewelryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostJewelryComponent]
    });
    fixture = TestBed.createComponent(PostJewelryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
