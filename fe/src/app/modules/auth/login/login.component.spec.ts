import { ComponentFixture, TestBed } from '@angular/core/testing';

// @ts-ignore
import { ButtonModule, CardModule, FormModule, GridModule } from '@coreui/angular-pro';
import { LoginComponent } from './login.component';
// @ts-ignore
import { IconModule } from '@coreui/icons-angular';
// @ts-ignore
import { IconSetService } from '@coreui/icons-angular';
// @ts-ignore
import { iconSubset } from '../../../core/icons/icon-subset';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let iconSetService: IconSetService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [FormModule, CardModule, GridModule, ButtonModule, IconModule],
      providers: [IconSetService]
    })
    .compileComponents();
  });

  beforeEach(() => {
    iconSetService = TestBed.inject(IconSetService);
    iconSetService.icons = { ...iconSubset };

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
