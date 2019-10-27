import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';



@Component({
  selector: 'reg-translate',
  templateUrl: './translate.component.html',
  styleUrls: [ './translate.component.css' ]
})
export class TranslateComponent implements OnInit {

  lang = 'en';


  constructor(private translate: TranslateService) {
    const defaultLang = 'ru';
    translate.setDefaultLang(defaultLang);
    translate.use(defaultLang);
    this.lang = defaultLang;
  }


  ngOnInit() {
  }


  changeLanguage() {
    if (this.lang === 'en') {
      this.lang = 'ru';
    } else {
      this.lang = 'en';
    }
    this.translate.use(this.lang);
  }

}
