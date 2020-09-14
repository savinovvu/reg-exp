import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProposeTaskComponent } from './propose-task.component';

describe('ProposeTaskComponent', () => {
  let component: ProposeTaskComponent;
  let fixture: ComponentFixture<ProposeTaskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProposeTaskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProposeTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
