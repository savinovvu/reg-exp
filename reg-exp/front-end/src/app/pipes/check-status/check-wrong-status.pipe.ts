import { Pipe, PipeTransform } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';


@Pipe({
  name: 'checkWrongStatus'
})
export class CheckWrongStatusPipe implements PipeTransform {

  constructor(private translate: TranslateService) {
  }

  // todo: make i18n with "check-wrond-status" label
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
