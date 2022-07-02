import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Books } from '../_model/books';
import { Borrow } from '../_model/borrow';
import { Users } from '../_model/users';
import { BooksService } from '../_service/books.service';
import { BorrowService } from '../_service/borrow.service';
import { UsersService } from '../_service/users.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

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
    this.id = this.route.snapshot.params['bookId'];
    // console.log(this.id);
    this.book = new Books();
    this.bookService.getBookById(this.id).subscribe( data => {
      this.book = data;
      console.log(data);
    })

    this.getBorrowHistory(this.id);
    
  }

  private getBorrowHistory(bookId: number) {
    this.borrowService.getBookBorrowHistory(bookId).subscribe(data => {
      this.borrow = data;
      console.log(data);
    });
  }

  public getUserData(userId: number):string {
    this.user = new Users();
    this.userService.getUserById(userId).subscribe( data => {
      this.user = data;
    })
    return this.user.name;
  }
}
