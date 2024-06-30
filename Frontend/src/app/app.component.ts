import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { StorageService } from './auth/services/storage/storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'School-Management-System-Frontend';

  isAdminLoggedIn:boolean;
  isStudentLoggedIn:boolean;

  constructor(
    private router: Router
  ){}

  ngOnInit(){
    this.updateUserLoggedInStatus();
    this.router.events.subscribe(event =>{
      if(event instanceof NavigationEnd){
        this.updateUserLoggedInStatus();
      }
    })
  }

  private updateUserLoggedInStatus(): void {
    this.isAdminLoggedIn=StorageService.isAdminLoggedIn();
    this.isStudentLoggedIn=StorageService.isStudentLoggedIn();
  }

  logout(){
    StorageService.logout();
    this.router.navigateByUrl("/login");
  }
}
