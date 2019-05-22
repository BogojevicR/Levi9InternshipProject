import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CategoryComponent } from 'src/app/category/category.component';

import { from } from 'rxjs';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AddCategoryComponent } from './add-category/add-category.component';

const routes: Routes = [
  
  { path : "category", component: CategoryComponent},
  { path : "admin", component: AdminHomeComponent},
  { path : "category/add", component: AddCategoryComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
