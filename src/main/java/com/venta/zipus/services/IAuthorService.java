package com.venta.zipus.services;

import com.venta.zipus.models.authors.Author;

public interface IAuthorService {
    Author getAuthorById(long id);
}
