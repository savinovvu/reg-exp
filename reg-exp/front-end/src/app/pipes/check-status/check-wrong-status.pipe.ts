import { Pipe, PipeTransform } from '@angular/core';



@Pipe({
  name: 'checkWrongStatus'
})
export class CheckWrongStatusPipe implements PipeTransform {

  transform(value: string): string {
    switch (value) {
      case 'Unmatch':
        return 'Не нашло:';
      case 'Match':
        return 'Нашло:';
      case 'Unused':
        return 'не использовало:';
      case 'Equals':
        return 'не должно совпадать:';
    }
    return null;
  }

}
