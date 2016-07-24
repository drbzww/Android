/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /media/2Tdisk/mygithub/Android/TestOtherService/src/com/cytmxk/testotherservice/IInfoService.aidl
 */
package com.cytmxk.testservice;
public interface IInfoService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.cytmxk.testservice.IInfoService
{
private static final java.lang.String DESCRIPTOR = "com.cytmxk.testotherservice.IInfoService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.cytmxk.testotherservice.IInfoService interface,
 * generating a proxy if needed.
 */
public static com.cytmxk.testservice.IInfoService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.cytmxk.testservice.IInfoService))) {
return ((com.cytmxk.testservice.IInfoService)iin);
}
return new com.cytmxk.testservice.IInfoService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getProcessID:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getProcessID();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getThreadID:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getThreadID();
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.cytmxk.testservice.IInfoService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String getProcessID() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getProcessID, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getThreadID() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getThreadID, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getProcessID = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getThreadID = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public java.lang.String getProcessID() throws android.os.RemoteException;
public java.lang.String getThreadID() throws android.os.RemoteException;
}
