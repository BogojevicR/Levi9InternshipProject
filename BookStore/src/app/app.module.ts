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

import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AddCategoryComponent } from './add-category/add-category.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AdminBookComponent } from './admin-book/admin-book.component';
import { AdminBookEditComponent } from './admin-book-edit/admin-book-edit.component';
import { AdminBookAddComponent } from './admin-book-add/admin-book-add.component';

import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { AdminBookUpdateComponent } from './admin-book-update/admin-book-update.component';
import { ProfileComponent } from './profile/profile.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';





@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BookComponent,
    NavbarComponent,
    SidebarComponent,
    CategoryComponent,
    CategoryComponent,
    AdminHomeComponent,
    AddCategoryComponent,
    AdminBookComponent,
    AdminBookEditComponent,
    AdminBookAddComponent,
    ShoppingCartComponent,
    AdminBookUpdateComponent,
    ProfileComponent,
    AdminUsersComponent



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

    AlertModule.forRoot(),
    RouterModule.forRoot([
      {
        path:'',
        component: HomeComponent,

      },
      {
        path:'cart',
        component: ShoppingCartComponent
      },
      {
        path:'profile',
        component: ProfileComponent
      },
      {
        path:'users',
        component: AdminUsersComponent
      }
    ])
  ],
  providers: [BookService,CategoryServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
