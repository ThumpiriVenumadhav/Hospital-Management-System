import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StorageService } from '../../../auth/services/storage.service';

const BASIC_URL = "http://localhost:8765/"; 

@Injectable({
  providedIn: 'root'
})
export class AdminService {


 constructor(private http: HttpClient) { }


addDoctor(doctor: any): Observable<any> {
  return this.http.post(BASIC_URL + "api/v1/doctor", doctor, {
    headers: this.createAuthorizationHeader()
  });
}


  addPatient(patient: any) {
  return this.http.post(BASIC_URL + 'api/v1/patient', patient, {
    headers: this.createAuthorizationHeader()
  });
}



  getAllDoctors():Observable<any>{
    return this.http.get(BASIC_URL + "api/v1/doctor",{
      headers:this.createAuthorizationHeader()
    });
  }


   getAllPatients():Observable<any>{
    return this.http.get(BASIC_URL + "api/v1/patient",{
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
