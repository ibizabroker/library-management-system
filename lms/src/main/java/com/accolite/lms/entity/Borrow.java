package com.accolite.lms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Borrow")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer borrowId;
    Date issueDate;
    Date dueDate;
    Date returnDate;

}
