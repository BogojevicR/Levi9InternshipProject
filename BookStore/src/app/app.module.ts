import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AlertModule } from 'ngx-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CategoryComponent } from './category/category.component';
import { HttpClientModule } from '@angular/common/http';
import { CategoryServiceService } from './services/category-service.service';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AddCategoryComponent } from './add-category/add-category.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    CategoryComponent,
    AdminHomeComponent,
    AddCategoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpClientModule,
    AlertModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [CategoryServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
