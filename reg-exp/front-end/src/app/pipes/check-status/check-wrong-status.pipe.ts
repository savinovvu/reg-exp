import { Pipe, PipeTransform } from '@angular/core';



@Pipe({
  name: 'checkWrongStatus'
})
export class CheckWrongStatusPipe implements PipeTransform {

  transform(value: string): string {
    switch (value) {
      case 'Unmatch':
        return 'Не нашел:';
      case 'Match':
        return 'Нашел:';
      case 'Unused':
        return 'не использовал:';
      case 'Equals':
        return 'не должен совпадать:';
      case 'NotAnswer':
        return 'не был предложен:';
    }
    return null;
  }

}
