package cainiaolicai;

/**
 * @author zhangzhiqiang
 * @date 2018-10-18 16:25
 * &Desc mq消息发送方
 */
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;
import util.OperateOracle;

public class SendMessage {
    private static final String url = "tcp://120.76.132.101:61616";
    private static final String QUEUE_NAME = "post_queues";

    /**
     * 发送文本消息
     * @param messages
     * @throws JMSException
     */
    public void sendTextMessage(List<String> messages) throws JMSException {
        // JMS 客户端到JMSProvider 的连接
        Connection connection = null;
        try {
            // 连接工厂，JMS 用它创建连接
            // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = (Connection) connectionFactory.createConnection();
            // 启动连接
            connection.start();
            //Session：发送或接收消息的线程
            // 获取session
            Session session = (Session) connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            // 消息的目的地，消息发送到那个队列
            Destination destination = session.createQueue(QUEUE_NAME);
            //MessageProducer：消息发送者（生产者）
            // 创建消息发送者
            MessageProducer producer = session.createProducer(destination);
            // 设置是否持久化
            //DeliveryMode.NON_PERSISTENT：不持久化
            //DeliveryMode.PERSISTENT：持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            String msg = "";
            int i = 0;
//            do {
//                msg = "第" + i + "次发送的消息：" + new Random();
//                TextMessage message = session.createTextMessage(msg);
//                Thread.sleep(1000);
//                // 发送消息到目的地方
//                producer.send(message);
//                System.out.println("发送消息：" + msg);
//                i++;
//            } while (i < 1000);
            for (String message:messages) {
                TextMessage textMessage = session.createTextMessage(message);
                producer.send(textMessage);
            }
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送键值对消息
     * @param mapMessages
     * @throws JMSException
     */
    public void sendMapMessage(Map<Object,Object> mapMessages) throws JMSException {
        // JMS 客户端到JMSProvider 的连接
        Connection connection = null;
        try {
            // 连接工厂，JMS 用它创建连接
            // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = (Connection) connectionFactory.createConnection();
            // 启动连接
            connection.start();
            //Session：发送或接收消息的线程
            // 获取session
            Session session = (Session) connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            // 消息的目的地，消息发送到那个队列
            Destination destination = session.createQueue(QUEUE_NAME);
            //MessageProducer：消息发送者（生产者）
            // 创建消息发送者
            MessageProducer producer = session.createProducer(destination);
            // 设置是否持久化
            //DeliveryMode.NON_PERSISTENT：不持久化
            //DeliveryMode.PERSISTENT：持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

//            String msg = "";
//            int i = 0;
//            do {
//                msg = "第" + i + "次发送的消息：" + new Random();
//                TextMessage message = session.createTextMessage(msg);
//                Thread.sleep(1000);
//                // 发送消息到目的地方
//                producer.send(message);
//                System.out.println("发送消息：" + msg);
//                i++;
//            } while (i < 1000);
            MapMessage mapMessage = session.createMapMessage();
            for (Map.Entry entry:mapMessages.entrySet()) {
                mapMessage.setIntProperty((String) entry.getValue(),(Integer) entry.getKey());
                producer.send(mapMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 发送对象消息
     * @param objMessages
     * @throws JMSException
     */
    public void sendObjectMessage(Object[] objMessages) throws JMSException {
        // JMS 客户端到JMSProvider 的连接
        Connection connection = null;
        try {
            // 连接工厂，JMS 用它创建连接
            // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = (Connection) connectionFactory.createConnection();
            // 启动连接
            connection.start();
            //Session：发送或接收消息的线程
            // 获取session
            Session session = (Session) connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            // 消息的目的地，消息发送到那个队列
            Destination destination = session.createQueue(QUEUE_NAME);
            //MessageProducer：消息发送者（生产者）
            // 创建消息发送者
            MessageProducer producer = session.createProducer(destination);
            // 设置是否持久化
            //DeliveryMode.NON_PERSISTENT：不持久化
            //DeliveryMode.PERSISTENT：持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            String msg = "";
            int i = 0;
            do {
                msg = "第" + i + "次发送的消息：" + new Random();
                TextMessage message = session.createTextMessage(msg);
                Thread.sleep(1000);
                // 发送消息到目的地方
                producer.send(message);
                System.out.println("发送消息：" + msg);
                i++;
            } while (i < 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OperateOracle op = new OperateOracle();
        //先获取数据库爬取的帖子放入队列
        List<String> posts = op.getPosts();
        SendMessage sndMsg = new SendMessage();
        try {
            sndMsg.sendTextMessage(posts);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}