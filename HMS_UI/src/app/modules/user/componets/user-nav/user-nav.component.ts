import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MaterialModule } from '../../../../material.module';
import { CommonModule } from '@angular/common';
import { StorageService } from '../../../../auth/services/storage.service';

@Component({
  selector: 'app-user-nav',
  standalone: true,
  imports: [CommonModule, MaterialModule, RouterLink],
  templateUrl: './user-nav.component.html',
  styleUrls: ['./user-nav.component.css']
})
export class UserNavComponent {

  isUserLoggedIn: boolean = StorageService.isUserLoggedIn();

  constructor(private router: Router) {
    this.router.events.subscribe(() => {
      this.isUserLoggedIn = StorageService.isUserLoggedIn();
    });
  }

  logout(): void {
    StorageService.logout();
    this.router.navigateByUrl('/login');
  }
}
