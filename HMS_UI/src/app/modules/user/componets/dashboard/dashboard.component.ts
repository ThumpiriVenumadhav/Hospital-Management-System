import { Component } from '@angular/core';
import { UserNavComponent } from "../user-nav/user-nav.component";

@Component({
  selector: 'app-dashboard',
  imports: [UserNavComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
