package kimyh.springbasic.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    public void connect() {
        System.out.println("connect = " + url);
    }

    public void disconnect() {
        System.out.println("close = " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetWorkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetWorkClient.close");
        disconnect();
    }


//    public void init() {
//        System.out.println("NetWorkClient.init");
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    public void close() {
//        System.out.println("NetWorkClient.close");
//        disconnect();
//}

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }


}
