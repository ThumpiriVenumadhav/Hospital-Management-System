import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MaterialModule } from '../../../../material.module';
import { UserService } from '../../services/user.service';
import { UserNavComponent } from "../user-nav/user-nav.component"; // or create UserService

@Component({
  selector: 'app-book-appointment',
  standalone: true,
  imports: [CommonModule, FormsModule, MaterialModule, UserNavComponent],
  templateUrl: './book-appointment.component.html',
  styleUrls: ['./book-appointment.component.css']
})
export class BookAppointmentComponent implements OnInit {

  doctors: any[] = [];
  appointment: any = {
    doctorId: null,
    date: '',
    time: ''
  };

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadDoctors();
  }

  loadDoctors(): void {
    this.userService.getAllDoctors().subscribe({
      next: (res: any) => {
        this.doctors = res;
      },
      error: (err: any) => {
        console.error('Error fetching doctors:', err);
      }
    });
  }

  bookAppointment(): void {
    if (!this.appointment.doctorId || !this.appointment.date || !this.appointment.time) {
      alert('Please fill all fields before booking');
      return;
    }

    this.userService.bookAppointment(this.appointment).subscribe({
      next: () => {
        alert('Appointment booked successfully!');
        this.router.navigate(['/user/dashboard']);
      },
      error: (err: any) => {
        console.error('Error booking appointment:', err);
        alert('Failed to book appointment');
      }
    });
  }
}
