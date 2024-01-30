package ru.gb.SpringREST.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Data
@AllArgsConstructor
public class Issue2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BOOK_ID")
    private long bookId;

    @Column(name = "READER_ID")
    private long readerId;

    @Column(name = "TIMESTAMP")
    private LocalDateTime timestamp;

    public Issue2(long bookId, long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.timestamp = LocalDateTime.now();
    }

    public Issue2() {

    }
}
