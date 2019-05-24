import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CategoryComponent } from 'src/app/category/category.component';

import { from } from 'rxjs';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AddCategoryComponent } from './add-category/add-category.component';
import { AdminBookComponent } from './admin-book/admin-book.component';
import { AdminBookAddComponent } from './admin-book-add/admin-book-add.component';
import { AdminBookEditComponent } from './admin-book-edit/admin-book-edit.component';
import { AdminBookUpdateComponent } from './admin-book-update/admin-book-update.component';

const routes: Routes = [
  
  { path : "category", component: CategoryComponent},
  { path : "admin", component: AdminHomeComponent},
  { path : "category/add", component: AddCategoryComponent},
  { path : "adminBook", component : AdminBookComponent},
  { path : "adminBook/add", component : AdminBookAddComponent},
  { path : "adminBook/update/:id", component : AdminBookUpdateComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
