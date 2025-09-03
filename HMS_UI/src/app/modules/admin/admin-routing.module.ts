import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AddDoctorComponent } from './components/add-doctor/add-doctor.component';

const routes: Routes = [
  {path:"dashboard",component:DashboardComponent},
  {path:"adddoctor",component:AddDoctorComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
