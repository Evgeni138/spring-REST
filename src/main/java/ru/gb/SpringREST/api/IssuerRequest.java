package ru.gb.SpringREST.api;

import lombok.Data;

@Data
public class IssuerRequest {
    private long readerId;
    private long bookId;
}
