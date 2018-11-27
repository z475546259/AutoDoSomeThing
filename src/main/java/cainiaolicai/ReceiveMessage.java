package cainiaolicai;

/**
 * @author zhangzhiqiang
 * @date 2018-10-18 17:33
 * &Desc activemq接受消息方
 */
import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息接收类
 *
 * @createTime:Apr 7, 2013 5:11:11 PM
 * @author:<a href="mailto:252909344@qq.com">迷蝶</a>
 * @version:0.1
 *@lastVersion: 0.1
 * @updateTime:
 *@updateAuthor: <a href="mailto:252909344@qq.com">迷蝶</a>
 * @changesSum:
 *
 */
public class ReceiveMessage {
    private static final String url = "tcp://120.76.132.101:61616";
    private static final String QUEUE_NAME = "post_queues";

    public void receiveMessage() {
        Connection connection = null;
        try {
            try {
                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                        url);
                connection = connectionFactory.createConnection();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            connection.start();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            //消息接收者，也就是消费者
            MessageConsumer consumer = session.createConsumer(destination);

            consumeMessagesAndClose(connection, session, consumer);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 接收和关闭消息，如遇到消息内容为close则，关闭连接
     *
     * @param connection JMS 客户端到JMSProvider 的连接
     * @param session    发送或接收消息的线程
     * @param consumer   消息接收对象
     * @throws JMSException
     * @auther <ahref="mailto:252909344@qq.com">迷蝶</a>
     * Apr 8, 2013 10:31:55 AM
     */
    protected void consumeMessagesAndClose(Connection connection,
                                           Session session, MessageConsumer consumer) throws JMSException {
        do {
            Message message = consumer.receive(1000);
            if ("close".equals(message)) {
                consumer.close();
                session.close();
                connection.close();
            }
            if (message != null) {
                onMessage(message);
            }
        } while (true);
    }

    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage txtMsg = (TextMessage) message;
                String msg = txtMsg.getText();
                System.out.println("Received:" + msg);
            }else if(message instanceof MapMessage){
//                MapMessage mapMessage = (MapMessage) message;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getOnePost(){
        Connection connection = null;
        String post = "";
        try {
            try {
                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                        url);
                connection = connectionFactory.createConnection();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            connection.start();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            //消息接收者，也就是消费者
            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive(1000);
            if (message != null) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage txtMsg = (TextMessage) message;
                        post = txtMsg.getText();
                        System.out.println("Received:" + post);
                    }else if(message instanceof MapMessage){
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return post;
    }

    public static void main(String args[]) {
        ReceiveMessage rm = new ReceiveMessage();
//        rm.receiveMessage();
        System.out.println("获取："+rm.getOnePost());
    }
}