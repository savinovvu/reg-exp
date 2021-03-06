import { OnDestroy, OnInit } from '@angular/core';



export abstract class BaseComponent implements OnInit, OnDestroy {

  private subs: Array<any> = [];


  set subscribtion(subscription: any) {
    this.subs.push(subscription);
  }


  ngOnInit() {
  }


  ngOnDestroy() {
    for (const sub of this.subs) {
      if (sub) { sub.unsubscribe(); }
    }
  }


}
