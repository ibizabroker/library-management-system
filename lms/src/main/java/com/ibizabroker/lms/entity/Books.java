package com.ibizabroker.lms.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer bookId;
    String bookName;
    String bookAuthor;
    String bookGenre;
    Integer noOfCopies;

    public void borrowBook() {
        this.noOfCopies--;
    }

    public void returnBook() {
        this.noOfCopies++;
    }

}
