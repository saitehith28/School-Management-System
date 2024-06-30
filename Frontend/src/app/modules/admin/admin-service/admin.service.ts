import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth/services/storage/storage.service';


const BASIC_URL=["http://localhost:8080/"];

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  addStudent(studentDto: any): Observable<any>{
    console.log(this.createAuthorizationHeader());
    return this.http.post<[]>(BASIC_URL+"api/admin/student",studentDto,{
      headers:this.createAuthorizationHeader(),
    });
  }

  getAllStudents(): Observable<any> {
    return this.http.get<[]>(BASIC_URL + "api/admin/students",
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  deleteStudent(studentId: any): Observable<any> {
    return this.http.delete<[]>(BASIC_URL + `api/admin/student/${studentId}`,
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  getStudentById(studentId: number): Observable<any> {
    return this.http.get<[]>(BASIC_URL + `api/admin/student/${studentId}`,
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  updateStudent(studentId: number, studentDto: any): Observable<any>{
    console.log(this.createAuthorizationHeader());
    return this.http.put<[]>(BASIC_URL+`api/admin/student/${studentId}`,studentDto,{
      headers:this.createAuthorizationHeader(),
    });
  }

  payFee(studentId: number, feeDto: any): Observable<any>{
    console.log(this.createAuthorizationHeader());
    return this.http.post<[]>(BASIC_URL+`api/admin/fee/${studentId}`,feeDto,{
      headers:this.createAuthorizationHeader(),
    });
  }

  createAuthorizationHeader(): HttpHeaders{
    let authHeaders: HttpHeaders=new HttpHeaders();
    return authHeaders.set(
      'Authorization',"Bearer " + StorageService.getToken()
    );
  }
  // createAuthorizationHeader(): HttpHeaders {
  //   let authHeaders: HttpHeaders=new HttpHeaders();
  //   return authHeaders.set(
  //     'Authorization',"Bearer " + StorageService.getToken()
  //   )
  // }
}
