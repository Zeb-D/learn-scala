//package com.yd.scala.grpc.qryaccount;
//
//import io.grpc.stub.StreamObserver;
//
//public class QryAccountServiceImpl implements QryAccountService {
//
//    public void qry(Apiserver.AccountQryRequest request, StreamObserver<Apiserver.AccountQryResponse> responseObserver) {
//        System.out.println("qry " + request.getUserId());
//        Apiserver.AccountQryResponse response = Apiserver.AccountQryResponse.newBuilder().setRc(1).setAmount(666).build();
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//    }
//}