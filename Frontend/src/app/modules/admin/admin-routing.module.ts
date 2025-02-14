import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './admin-components/dashboard/dashboard.component';
import { AdminGuard } from 'src/app/auth/guards/admin-guard/admin.guard';
import { PostStudentComponent } from './admin-components/post-student/post-student.component';
import { AllStudentsComponent } from './admin-components/all-students/all-students.component';
import { UpdateStudentComponent } from './admin-components/update-student/update-student.component';
import { PayFeeComponent } from './admin-components/pay-fee/pay-fee.component';

const routes: Routes = [
  { path:"dashboard",component:DashboardComponent, canActivate: [AdminGuard] },
  { path:"student",component:PostStudentComponent, canActivate: [AdminGuard] },
  { path:"students",component:AllStudentsComponent, canActivate: [AdminGuard] },
  { path:"student/:studentId",component:UpdateStudentComponent, canActivate: [AdminGuard] },
  { path:"fee/:studentId",component:PayFeeComponent, canActivate: [AdminGuard] },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
