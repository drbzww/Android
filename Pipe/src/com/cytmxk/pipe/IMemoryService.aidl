package com.cytmxk.pipe;

interface IMemoryService {

    ParcelFileDescriptor getFileDescriptor();
    void setValue(int val);
}