import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Users } from '../_model/users';
import { UsersService } from '../_service/users.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  users: Users[];

  constructor(private usersService: UsersService,
    private router: Router) { }

  ngOnInit(): void {
    this.getUsers();
    // this.users = [{
    //   "userId": 1,
    //   "name": "tarun",
    //   "username": "tarungowda",
    //   "role": "STUDENT",
    //   "password": "sdklfjlakdsf"
    // }]
  }

  private getUsers() {
    this.usersService.getUsersList().subscribe(data =>{
      this.users = data;
      console.log(this.users);
    });
  }

  userDetails(userId: number) {
    this.router.navigate(['user-details', userId ]);
  }

  updateUser(userId: number) {
    this.router.navigate(['update-user', userId ]);
  }

}
