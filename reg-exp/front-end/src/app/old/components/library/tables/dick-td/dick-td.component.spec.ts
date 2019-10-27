import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DickTdComponent } from './dick-td.component';

describe('DickTdComponent', () => {
  let component: DickTdComponent;
  let fixture: ComponentFixture<DickTdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DickTdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DickTdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
