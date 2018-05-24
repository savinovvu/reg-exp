import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegexpCheckResultComponent } from './regexp-check-result.component';



describe('RegexpCheckResultComponent', () => {
  let component: RegexpCheckResultComponent;
  let fixture: ComponentFixture<RegexpCheckResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegexpCheckResultComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegexpCheckResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
