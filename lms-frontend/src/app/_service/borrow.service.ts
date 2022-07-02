import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Borrow } from '../_model/borrow';

@Injectable({
  providedIn: 'root'
})
export class BorrowService {

  private baseURL = "http://localhost:8080/borrow";

  constructor(private httpClient: HttpClient) { }

  getBorrowList(): Observable<Borrow[]> {
    return this.httpClient.get<Borrow[]>(`${this.baseURL}`);
  }

  borrowBook(borrow: Borrow): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, borrow);
  }

  returnBook(borrow: Borrow): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}`, borrow);
  }

  getBooksBorrowedByUser(userId: number): Observable<Borrow[]> {
    return this.httpClient.get<Borrow[]>(`${this.baseURL}/user/${userId}`);
  }

  getBookBorrowHistory(bookId: number): Observable<Borrow[]> {
    return this.httpClient.get<Borrow[]>(`${this.baseURL}/book/${bookId}`);
  }
}
