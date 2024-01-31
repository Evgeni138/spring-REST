package ru.gb.SpringREST.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Data
@AllArgsConstructor
@Schema(name = "Выдачи книг на руки")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Schema(name = "Выдача книги")
    private Long id;

    @Column(name = "BOOK_ID")
    @Schema(name = "Идентификатор выдачи книги")
    private long bookId;

    @Column(name = "READER_ID")
    @Schema(name = "Идентификатор читателя, которому выдана книга")
    private long readerId;

    @Column(name = "TIMESTAMP")
    @Schema(name = "Время, когда была выдана книга")
    private LocalDateTime timestamp;

    public Issue(long bookId, long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.timestamp = LocalDateTime.now();
    }

    public Issue() {

    }
}
