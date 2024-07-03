import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Fashion01Component } from './fashion01.component';

describe('Fashion01Component', () => {
  let component: Fashion01Component;
  let fixture: ComponentFixture<Fashion01Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [Fashion01Component]
    });
    fixture = TestBed.createComponent(Fashion01Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
