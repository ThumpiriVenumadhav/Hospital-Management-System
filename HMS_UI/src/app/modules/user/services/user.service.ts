import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StorageService } from '../../../auth/services/storage.service';

const BASIC_URL = "http://localhost:8765/";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  constructor(private http: HttpClient) { }
  
  bookAppointment(appointment: any) {
    return this.http.post(BASIC_URL + 'api/v1/appointments', appointment, {
      headers: this.createAuthorizationHeader()
    });
  }
  
  getAllDoctors():Observable<any>{
      return this.http.get(BASIC_URL + "api/v1/doctor",{
        headers:this.createAuthorizationHeader()
      });
    }


  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set(
      'Authorization',
      'Bearer ' + StorageService.getToken()
    );
  }
}
