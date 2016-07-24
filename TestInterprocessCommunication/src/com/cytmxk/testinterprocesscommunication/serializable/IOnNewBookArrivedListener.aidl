package com.cytmxk.testinterprocesscommunication.serializable;

import com.cytmxk.testinterprocesscommunication.serializable.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}