package com.cytmxk.ashmem;

interface IMemoryService {

    ParcelFileDescriptor getFileDescriptor();
    void setValue(int val);
}