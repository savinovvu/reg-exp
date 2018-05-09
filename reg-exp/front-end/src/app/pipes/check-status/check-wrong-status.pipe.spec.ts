import { CheckWrongStatusPipe } from './check-wrong-status.pipe';

describe('CheckWrongStatusPipe', () => {
  it('create an instance', () => {
    const pipe = new CheckWrongStatusPipe();
    expect(pipe).toBeTruthy();
  });
});
