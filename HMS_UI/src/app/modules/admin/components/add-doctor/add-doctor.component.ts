import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AdminService } from '../../services/admin.service';
import { MaterialModule } from '../../../../material.module';
import { AdminNavComponent } from "../admin-nav/admin-nav.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-doctor',
  standalone: true,   // Angular 15+ standalone component
  imports: [MaterialModule, ReactiveFormsModule, AdminNavComponent,CommonModule],  // ✅ Add ReactiveFormsModule here
  templateUrl: './add-doctor.component.html',
  styleUrls: ['./add-doctor.component.css']
})
export class AddDoctorComponent implements OnInit {

  doctorForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {
    this.doctorForm = this.fb.group({
      name: ['', Validators.required],
      specialization: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.doctorForm.valid) {
      const doctorData = this.doctorForm.value;

      this.adminService.addDoctor(doctorData).subscribe({
        next: (response) => {
          console.log('Doctor saved successfully:', response);
          alert('Doctor added successfully!');
          this.doctorForm.reset();
        },
        error: (err) => {
          console.error('Error while saving doctor:', err);
          alert('Failed to add doctor. Please try again.');
        }
      });
    } else {
      console.log('Form is invalid');
    }
  }
}
