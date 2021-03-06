package com.ltsh.common.client.activemq;

//import com.google.gson.Gson;
import lombok.Data;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import javax.jms.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengjb-it on 2016/10/27 0027.
 */
@Configuration
@EnableAutoConfiguration
@Component
@Data
public class ActiveMQUtils {
    //默认连接用户名
    @Value("activemq.userName")
    private String userName = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    @Value("activemq.password")
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    @Value("activemq.brokeUrl")
    private String brokeUrl = "failover:(tcp://192.168.22.214:61616)?randomize=false";
    //
    private int acknowledgeMode = Session.AUTO_ACKNOWLEDGE;
    private Long pollTime = 20 * 1000L;
//    private Gson gson = new Gson();


    private Session session;
    public static Map<String, MessageConsumer> consumerHashMap = new HashMap<String, MessageConsumer>();
    public Session getSession() {
        if(session == null) {
            //连接工厂
            ConnectionFactory connectionFactory;
            //连接
            Connection connection = null;
            //实例化连接工厂
            connectionFactory = new ActiveMQConnectionFactory(userName, password, brokeUrl);
            //通过连接工厂获取连接
            try {
                connection = connectionFactory.createConnection();
                //启动连接
                connection.start();
                //创建session
                session = connection.createSession(true, acknowledgeMode);
            } catch (JMSException e) {
                LogUtils.error(e.getMessage(), e);
            }

        }
        return session;
    }

    /**
     * 发送消息
     * @param messageEntity
     * @throws Exception
     */
    public void sendMessage(String formUser,Object messageEntity) throws Exception {
        Session session = getSession();
        Queue queue = session.createQueue(getQueueName(formUser));
        MessageProducer producer = session.createProducer(queue);
//        messageEntity.setSendDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        TextMessage message = getSession().createTextMessage(JsonUtils.toJson(messageEntity));
        producer.send(message);
        session.commit();
    }
    /**
     * 获取消息
     * @param userCode
     * @throws Exception
     */
    public <T> T getMessage(String userCode, Class<T> classT) throws Exception{
        Session session = getSession();
        String queueName = getQueueName(userCode);
        Queue queue = null;
        MessageConsumer consumer = null;
        try {
            synchronized (consumerHashMap) {
                if(consumerHashMap.get(queueName) != null) {
                    consumerHashMap.get(queueName).close();
                    consumerHashMap.remove(queueName);
                }
                queue = session.createQueue(queueName);
                consumer = session.createConsumer(queue);

                consumerHashMap.put(queueName, consumer);
            }

            TextMessage textMessage = (TextMessage)consumer.receive(pollTime);
            session.commit();
            if(textMessage != null) {
                String text = textMessage.getText();
                if(!StringUtils.isEmpty(text)) {
                    return JsonUtils.fromJson(text, classT);
                }
            }

        } finally {
            if(consumerHashMap.get(queueName) != null) {
                consumerHashMap.get(queueName).close();
                consumerHashMap.remove(queueName);
            }
            if(consumer != null) {
                consumer.close();
            }
//            if(session != null) {
//
//            }

        }


        return null;
    }
    private final static String QUEUE_PRE = "QUEUE_PRE_";
    private String getQueueName(String userCode){
        return QUEUE_PRE + userCode;
    }



}
