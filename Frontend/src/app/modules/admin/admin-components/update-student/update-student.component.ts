import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.scss']
})
export class UpdateStudentComponent {


  CLASS:string[]=[
    "Play","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th"
  ];
  GENDER:string[]=[
    "Male","Female","Not Specified"
  ];

  studentId: number=this.activatedRoute.snapshot.params['studentId'];
  validateForm: FormGroup;
  isSpinning: boolean;

  constructor(
    private service: AdminService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(){
    this.validateForm=this.fb.group({
      email: ['',Validators.required],
      name: ['',Validators.required],
      fatherName: ['',Validators.required],
      motherName: ['',Validators.required],
      studentClass: ['',Validators.required],
      dob: ['',Validators.required],
      address: ['',Validators.required],
      gender: ['',Validators.required]
    })
    this.getStudentById();
  }

  getStudentById(){
    this.service.getStudentById(this.studentId).subscribe((res) => {
      const student=res.studentDto;
      this.validateForm.patchValue(student);
      console.log(res);
    })
  }

  updateStudent(){
    this.service.updateStudent(this.studentId,this.validateForm.value).subscribe(
      (res) => {
        console.log(res);
        if(res.id!=null){
          this.snackBar.open("Student Updated Successfully", "Close", { duration: 5000 });
        }else{
          this.snackBar.open("Student Not Found", "Close", { duration: 5000 });
        }
      }
    )
  }

}
