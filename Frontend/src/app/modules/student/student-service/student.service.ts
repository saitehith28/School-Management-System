import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth/services/storage/storage.service';


const BASIC_URL=["http://localhost:8080/"];

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  
  getStudentById(): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/student/${StorageService.getUserId()}`,
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  applyLeave(studentLeaveDto): Observable<any> {
    studentLeaveDto.userId=StorageService.getUserId();
    // studentLeaveDto.userId = 13;
    return this.http.post<[]>(BASIC_URL + `api/student/leave`, studentLeaveDto,
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  createAuthorizationHeader(): HttpHeaders{
    let authHeaders: HttpHeaders=new HttpHeaders();
    return authHeaders.set(
      'Authorization',"Bearer " + StorageService.getToken()
    );
  }
}
