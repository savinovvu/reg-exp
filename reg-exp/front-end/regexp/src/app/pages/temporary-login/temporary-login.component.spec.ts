import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TemporaryLoginComponent } from './temporary-login.component';

describe('TemporaryLoginComponent', () => {
  let component: TemporaryLoginComponent;
  let fixture: ComponentFixture<TemporaryLoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TemporaryLoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TemporaryLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
