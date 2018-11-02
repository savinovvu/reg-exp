import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DickThComponent } from './dick-th.component';

describe('DickThComponent', () => {
  let component: DickThComponent;
  let fixture: ComponentFixture<DickThComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DickThComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DickThComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
