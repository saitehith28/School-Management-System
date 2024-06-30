import { TestBed } from '@angular/core/testing';

import { noAuthGuard } from './no-auth.guard';

describe('NoAuthGuard', () => {
  let guard: noAuthGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(noAuthGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
