import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './componets/dashboard/dashboard.component';
import { BookAppointmentComponent } from './componets/book-appointment/book-appointment.component';

const routes: Routes = [
  {path:"dashboard",component:DashboardComponent},
  {path:"bookappointment",component:BookAppointmentComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
