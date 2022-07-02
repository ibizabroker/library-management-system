import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Books } from '../_model/books';
import { Borrow } from '../_model/borrow';
import { Users } from '../_model/users';
import { BooksService } from '../_service/books.service';
import { BorrowService } from '../_service/borrow.service';
import { UsersService } from '../_service/users.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  id: number;
  book: Books;
  borrow: Borrow[];
  user: Users;

  constructor(private route: ActivatedRoute,
    private bookService: BooksService,
    private borrowService: BorrowService,
    public userService: UsersService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['userId'];
    // console.log(this.id);
    this.user = new Users();
    this.userService.getUserById(this.id).subscribe( data => {
      this.user = data;
      console.log(data);
    })

    this.getBorrowedByUser(this.id);
    
  }

  private getBorrowedByUser(userId: number) {
    this.borrowService.getBooksBorrowedByUser(userId).subscribe(data => {
      this.borrow = data;
      console.log(data);
    });
  }

}
