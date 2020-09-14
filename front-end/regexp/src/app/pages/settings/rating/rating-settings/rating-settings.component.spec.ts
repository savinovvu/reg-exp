import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RatingSettingsComponent } from './rating-settings.component';

describe('RatingSettingsComponent', () => {
  let component: RatingSettingsComponent;
  let fixture: ComponentFixture<RatingSettingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RatingSettingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RatingSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
