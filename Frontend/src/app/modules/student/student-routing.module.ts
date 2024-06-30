import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './student-components/dashboard/dashboard.component';
import { StudentGuard } from 'src/app/auth/guards/student-guard/student.guard';
import { ApplyLeaveComponent } from './student-components/apply-leave/apply-leave.component';
const routes: Routes = [
  { path:"dashboard",component:DashboardComponent, canActivate: [StudentGuard] },
  { path:"leave",component:ApplyLeaveComponent, canActivate: [StudentGuard] },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
