package com.cytmxk.testinterprocesscommunication.serializable;

import com.cytmxk.testinterprocesscommunication.serializable.Book;
import com.cytmxk.testinterprocesscommunication.serializable.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}