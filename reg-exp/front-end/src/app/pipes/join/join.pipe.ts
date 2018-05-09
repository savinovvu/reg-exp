import { Pipe, PipeTransform } from '@angular/core';



@Pipe({
  name: 'join',
  pure: false
})
export class JoinPipe implements PipeTransform {

  transform(strings: string[], joiner: string): string {
    let result = strings.join(joiner + " ");
    return result;
  }

}
