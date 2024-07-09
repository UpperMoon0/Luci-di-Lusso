import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostDiamondComponent } from './post-diamond.component';

describe('PostDiamondComponent', () => {
  let component: PostDiamondComponent;
  let fixture: ComponentFixture<PostDiamondComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostDiamondComponent]
    });
    fixture = TestBed.createComponent(PostDiamondComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
