import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../../material.module';
import { AdminNavComponent } from '../admin-nav/admin-nav.component';
import { StorageService } from '../../../../auth/services/storage.service';

import { AdminService } from '../../services/admin.service';// <-- make sure path is correct

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [MaterialModule, CommonModule, RouterLink, AdminNavComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  isAdminLoggedIn: boolean = StorageService.isAdminLoggedIn();
  doctors: any[] = [];   // Store doctors fetched from API

  constructor(
    private router: Router,
    private adminService: AdminService
  ) {
    this.router.events.subscribe(() => {
      this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
    });
  }

  ngOnInit(): void {
    this.getDoctors();
  }

  getDoctors(): void {
    this.adminService.getAllDoctors().subscribe({
      next: (res: any) => {
        this.doctors = res;
      },
      error: (err: any) => {
        console.error('Error fetching doctors:', err);
      }
    });
  }

  logout(): void {
    StorageService.logout();
    this.router.navigateByUrl('/login');
  }
}
