//package com.mlv.learn.config;
//
//import org.apache.http.HttpHost;
//import org.apache.http.client.config.RequestConfig;
//import org.elasticsearch.client.Node;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author lyk
// * @date 2024-03-18 11:48
// * @description es连接配置
// */
//@Configuration
//public class ElasticsearchConfig {
//
//    private static final int ADDRESS_LENGTH = 1;
//    @Value("${elasticsearch.scheme:http}")
//    private String scheme;
//    @Value("${elasticsearch.cluster-nodes:119.84.70.166}")
//    private String hostName;
//    @Value("${elasticsearch.port:19200}")
//    private int port;
//    @Value("${elasticsearch.connection-request-timeout}")
//    private Integer connectionRequestTimeout;
//    @Value("${elasticsearch.socket-timeout}")
//    private Integer socketTimeout;
//    @Value("${elasticsearch.connect-timeout}")
//    private Integer connectTimeout;
//
//    @Bean
//    public RestClientBuilder restClientBuilder() {
//   /*     HttpHost[] hosts = Arrays.stream(ipAddress)
//                .map(this::makeHttpHost)
//                .filter(Objects::nonNull)
//                .toArray(HttpHost[]::new);*/
//        HttpHost hosts = new HttpHost(hostName, port, scheme);
//        RestClientBuilder restClientBuilder = RestClient.builder(hosts);
//        // 设置一个监听器，每次节点出现故障时都会收到通知，以防需要采取措施，
//        // 当启用故障嗅探时在内部使用。
//        restClientBuilder.setFailureListener(new RestClient.FailureListener() {
//            @Override
//            public void onFailure(Node node) {
//
//            }
//        });
//        // 设置允许修改默认请求配置的回调
//        //（例如请求超时，身份验证或org.apache.http.client.config.RequestConfig.Builder允许设置的任何内容）。
//        restClientBuilder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
//            @Override
//            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
//                return requestConfigBuilder
//                        .setConnectionRequestTimeout(connectionRequestTimeout)
//                        .setSocketTimeout(socketTimeout)
//                        .setConnectTimeout(connectTimeout);
//            }
//        });
//        return restClientBuilder;
//    }
//
//    @Bean(name = "highLevelClient")
//    public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {
//        // TODO 此处可以进行其它操作
//        return new RestHighLevelClient(restClientBuilder);
//    }
//
//    /**
//     * 根据配置创建HttpHost
//     * @param s
//     * @return
//     */
//    private HttpHost makeHttpHost(String s) {
//        String[] address = s.split(":");
//        if (address.length == ADDRESS_LENGTH) {
//            String ip = address[0];
//            int port = Integer.parseInt(address[1]);
//            return new HttpHost(ip, port, scheme);
//        } else {
//            return null;
//        }
//    }
//}
