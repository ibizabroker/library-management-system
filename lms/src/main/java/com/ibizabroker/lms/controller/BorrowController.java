package com.ibizabroker.lms.controller;

import com.ibizabroker.lms.dao.BooksRepository;
import com.ibizabroker.lms.dao.BorrowRepository;
import com.ibizabroker.lms.dao.UsersRepository;
import com.ibizabroker.lms.entity.Books;
import com.ibizabroker.lms.entity.Borrow;
import com.ibizabroker.lms.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BooksRepository booksRepository;

    @PostMapping
    public String borrowBook(@RequestBody Borrow borrow) {
        Users user = usersRepository.findById(borrow.getUserId()).get();
        Books book = booksRepository.findById(borrow.getBookId()).get();

        if (book.getNoOfCopies() < 1) {
            return "The book \"" + book.getBookName() + "\" is out of stock!";
        }

        book.borrowBook();
        booksRepository.save(book);

        Date currentDate = new Date();
        Date overdueDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(overdueDate);
        c.add(Calendar.DATE, 7);
        overdueDate = c.getTime();
        borrow.setIssueDate(currentDate);
        borrow.setDueDate(overdueDate);
        borrowRepository.save(borrow);
        return user.getName() + " has borrowed one copy of \"" + book.getBookName() + "\"!";
    }

    @GetMapping
    public List<Borrow> getAllBorrow() {
        return borrowRepository.findAll();
    }

    @PutMapping
    public Borrow returnBook(@RequestBody Borrow borrow) {
        Borrow borrowBook = borrowRepository.findById(borrow.getBorrowId()).get();
        Books book = booksRepository.findById(borrowBook.getBookId()).get();

        book.returnBook();
        booksRepository.save(book);

        Date currentDate = new Date();
        borrowBook.setReturnDate(currentDate);
        return borrowRepository.save(borrowBook);
    }

    @GetMapping("user/{id}")
    public List<Borrow> booksBorrowedByUser(@PathVariable Integer id) {
        return borrowRepository.findByUserId(id);
    }

    @GetMapping("book/{id}")
    public List<Borrow> bookBorrowHistory(@PathVariable Integer id) {
        return borrowRepository.findByBookId(id);
    }


//    @Autowired
//    private EntityManager entityManager;
//
//    @PostMapping
//    public Borrow borrowBook(@RequestBody Borrow borrow) {
//        borrowRepository.save(borrow);
//        Books book = booksRepository.findById(borrow.getBOOKID()).orElseThrow(() -> new NotFoundException("Book not found."));
//        if(book.getNoOfCopies()-1 < 0) {
//            throw new IllegalStateException("There are no available books.");
//        }
//        book.borrowBook();
//        booksRepository.save(book);
//
//        return borrow;
//    }
//
//    @GetMapping
//    public List<Borrow> getAllBorrow() {
//        return borrowRepository.findAll();
//    }
//
//    @PutMapping
//    public Borrow returnBook(@RequestBody Borrow borrow) {
//        borrowRepository.save(borrow);
//        Books book = booksRepository.findById(borrow.getBOOKID()).orElseThrow(() -> new NotFoundException("Book not found."));
//        book.returnBook();
//        booksRepository.save(book);
//
//        Date currentDate = new Date(new java.util.Date().getTime());
//        borrow.setReturnDate(currentDate);
//        return borrow;
//    }
//
//    @GetMapping("user/{id}")
//    public List<Books> booksBorrowedByUser(@PathVariable Integer id) {
//        Query q = entityManager.createNativeQuery("SELECT * FROM BOOKS AS B, BORROW AS L WHERE B.book_id = L.BOOKID AND L.USERID = " + id);
//        List<Books> borrowedBooks = q.getResultList();
//        return borrowedBooks;
//    }
//
//    @GetMapping("book/{id}")
//    public List<Users> bookBorrowHistory(@PathVariable Integer id) {
//        Query q = entityManager.createNativeQuery("SELECT * FROM USERS AS U, BORROW AS L WHERE U.user_id = L.USERID AND L.BOOKID = " + id);
//        List<Users> usersList = q.getResultList();
//        return usersList;
//    }

//    @PostMapping
//    public Borrow borrowBook(@RequestBody Borrow borrow) {
//        borrow(borrow.getBorrowId(), borrow.getUser().getUserId(), borrow.getBook().getBookId());
//        return borrow;
//    }
//
//    @GetMapping
//    public List<Borrow> getAllBorrow() {
//        return borrowRepository.findAll();
//    }
//
//    @PutMapping
//    public Borrow returnBook(@RequestBody Borrow borrow) {
//        Books book = booksRepository.findById(borrow.getBook().getBookId()).orElseThrow(() -> new NotFoundException("Book not found."));
//        book.returnBook();
//        booksRepository.save(book);
//
//        Date currentDate = new Date(new java.util.Date().getTime());
//        borrow.setReturnDate(currentDate);
//        return borrowRepository.save(borrow);
//    }
//
//    @GetMapping("user/{id}")
//    public List<Books> booksBorrowedByUser(@PathVariable Integer id) {
//        Users user = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found."));
//        return user.getBooks();
//    }
//
//    @GetMapping("book/{id}")
//    public List<Users> bookBorrowHistory(@PathVariable Integer id) {
//        Books book = booksRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found."));
//        return book.getUsers();
//    }
//
//    public void borrow(Integer borrowId, Integer userId, Integer bookId) {
//        Users user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found."));
//        if(user.getBooks().stream().anyMatch(book -> Objects.equals(book.getBookId(), bookId))) {
//            throw new IllegalStateException("User already borrowed the book");
//        }
//
//        Books book = booksRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found."));
//        if(book.getNoOfCopies()-1 < 0) {
//            throw new IllegalStateException("There are no available books.");
//        }
//
//        book.getUsers().add(user);
//        book.setNoOfCopies(book.getNoOfCopies()-1);
//        booksRepository.save(book);
//
//        user.getBooks().add(book);
//        usersRepository.save(user);
//    }

}
