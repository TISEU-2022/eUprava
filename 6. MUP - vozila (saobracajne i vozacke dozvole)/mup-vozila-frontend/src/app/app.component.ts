import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mup-vozila-frontend';

  constructor() { }

  localStorageItem(id: string): any {
    return localStorage.getItem(id);
  }

}
