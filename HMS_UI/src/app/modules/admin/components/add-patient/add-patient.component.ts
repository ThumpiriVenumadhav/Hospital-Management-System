import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MaterialModule } from '../../../../material.module';
import { AdminService } from '../../services/admin.service';

import { AdminNavComponent } from "../admin-nav/admin-nav.component";

@Component({
  selector: 'app-add-patient',
  standalone: true,
  imports: [CommonModule, FormsModule, MaterialModule, AdminNavComponent],
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css']
})
export class AddPatientComponent {

  patient: any = {
    name: '',
    email: '',
    phone: '',
    age: null,
    gender: ''
  };

  constructor(
    private adminService: AdminService,
    private router: Router
  ) {}

  onSubmit(): void {
    if (!this.patient.name || !this.patient.email || !this.patient.phone || !this.patient.age || !this.patient.gender) {
      alert('Please fill all required fields');
      return;
    }

    this.adminService.addPatient(this.patient).subscribe({
      next: () => {
        alert('Patient added successfully!');
        this.router.navigate(['/admin/dashboard']); // ✅ go back to dashboard
      },
      error: (err) => {
        console.error('Error adding patient:', err);
        alert('Failed to add patient');
      }
    });
  }
}
