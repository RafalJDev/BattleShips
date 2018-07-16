import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteredPlayersComponent } from './registered-players.component';

describe('RegisteredPlayersComponent', () => {
  let component: RegisteredPlayersComponent;
  let fixture: ComponentFixture<RegisteredPlayersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisteredPlayersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisteredPlayersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
