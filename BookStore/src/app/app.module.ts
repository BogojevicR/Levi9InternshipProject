import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule} from '@angular/router'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { BookComponent } from './book/book.component';
import {BookService} from './services/book.service'
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AlertModule } from 'ngx-bootstrap';
import { CategoryComponent } from './category/category.component';
import { CategoryServiceService } from './services/category-service.service';
<<<<<<< HEAD
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AddCategoryComponent } from './add-category/add-category.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    CategoryComponent,
    AdminHomeComponent,
    AddCategoryComponent
=======
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BookComponent,
    NavbarComponent,
    SidebarComponent,
    CategoryComponent
>>>>>>> ce821a7c7ede169b62462000765bbe05a710f09d
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
<<<<<<< HEAD
    HttpClientModule,
    AlertModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
=======
    AlertModule.forRoot(),
    RouterModule.forRoot([
      {
        path:'',
        component: HomeComponent
      }
    ])
>>>>>>> ce821a7c7ede169b62462000765bbe05a710f09d
  ],
  providers: [BookService,CategoryServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
