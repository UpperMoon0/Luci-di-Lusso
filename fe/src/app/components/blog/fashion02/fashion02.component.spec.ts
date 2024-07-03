import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Fashion02Component } from './fashion02.component';

describe('Fashion02Component', () => {
  let component: Fashion02Component;
  let fixture: ComponentFixture<Fashion02Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [Fashion02Component]
    });
    fixture = TestBed.createComponent(Fashion02Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
