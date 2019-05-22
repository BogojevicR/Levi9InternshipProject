import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule} from '@angular/router'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { BookService } from './book.service';
import { BookComponent } from './book/book.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AlertModule } from 'ngx-bootstrap';
import { CategoryComponent } from './category/category.component';
import { CategoryServiceService } from './services/category-service.service';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BookComponent,
    NavbarComponent,
    SidebarComponent,
    CategoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AlertModule.forRoot(),
    RouterModule.forRoot([
      {
        path:'',
        component: HomeComponent
      }
    ])
  ],
  providers: [BookService,CategoryServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
