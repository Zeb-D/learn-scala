package com.yd.scala.webflux.websocket;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class EchoWebSocketHandler implements WebSocketHandler {
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(    //1. 向一个websocket连接发送一段消息
                session.receive()     //2. 获得入站消息流
                        .doOnNext(          //3. 对每一个websocket消息进行处理，相当于stream的map，返回的仍是一个流
                                WebSocketMessage::retain  //4. 保留消息（主要针对池化内存（内部使用了netty的ByteBuf），使之引用计数+1，避免过早回收）
                        )
        );
    }
}