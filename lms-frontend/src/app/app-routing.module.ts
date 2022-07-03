import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookDetailsComponent } from './book-details/book-details.component';
import { BooksListComponent } from './books-list/books-list.component';
import { BorrowBookComponent } from './borrow-book/borrow-book.component';
import { CreateBookComponent } from './create-book/create-book.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ReturnBookComponent } from './return-book/return-book.component';
import { UpdateBookComponent } from './update-book/update-book.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UsersListComponent } from './users-list/users-list.component';
import { AuthGuard } from './_auth/auth.guard';

const routes: Routes = [
  {path: 'books', component: BooksListComponent, canActivate:[AuthGuard], data:{roles:['Admin']}},
  {path: 'create-book', component: CreateBookComponent, canActivate:[AuthGuard], data:{roles:['Admin']}},
  {path: '', component: HomeComponent},
  {path: 'update-book/:bookId', component: UpdateBookComponent, canActivate:[AuthGuard], data:{roles:['Admin']}},
  {path: 'book-details/:bookId', component: BookDetailsComponent, canActivate:[AuthGuard], data:{roles:['Admin']}},
  {path: 'users', component: UsersListComponent, canActivate:[AuthGuard], data:{roles:['Admin']}},
  {path: 'register-user', component: RegistrationComponent, canActivate:[AuthGuard], data:{roles:['Admin']}},
  {path: 'user-details/:userId', component: UserDetailsComponent, canActivate:[AuthGuard], data:{roles:['Admin']}},
  {path: 'update-user/:userId', component: UpdateUserComponent, canActivate:[AuthGuard], data:{roles:['Admin']}},
  {path: 'login', component: LoginComponent},
  {path: 'forbidden', component: ForbiddenComponent},
  {path: 'borrow-book', component: BorrowBookComponent, canActivate:[AuthGuard], data:{roles:['User']}},
  {path: 'return-book', component: ReturnBookComponent, canActivate:[AuthGuard], data:{roles:['User']}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
