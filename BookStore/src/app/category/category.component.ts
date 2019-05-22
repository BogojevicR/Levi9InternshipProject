import { Component, OnInit } from '@angular/core';
import { CategoryServiceService } from '../services/category-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css',
]
})
export class CategoryComponent implements OnInit {

  constructor(private  catgoryService : CategoryServiceService , private router: Router) { }

  categories : any = [];

  ngOnInit() {
    console.log("Usao u getAll");
    this.catgoryService.getAllCategories().subscribe
      (data => {
        console.log(data);
        this.categories = data;
      });
  }



}
