package com.example.Book;
import jakarta.persistence.*;

@Entity


public class Book {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;


    }

