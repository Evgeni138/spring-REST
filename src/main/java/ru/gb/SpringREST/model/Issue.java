package ru.gb.SpringREST.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
/*
Запись о факте выдачи книги (в БД)
 */
@Data
@RequiredArgsConstructor
public class Issue {
    public static long sequence = 1L;

    private final long id;
    private final long bookId;
    private final long readerId;
    private final LocalDateTime timestamp; // дата выдачи

    public Issue(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.timestamp = LocalDateTime.now();
    }
}
