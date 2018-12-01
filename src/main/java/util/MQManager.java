package util;

import cainiaolicai.RecordToFile;
import com.zzq.springcloud.entities.cnUser;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.*;

public class MQManager {
    public static cnUser MQConsumerQueueReceiveCnUser(){
        cnUser cnUser = null;
        //1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://120.77.202.205:61616");
        ((ActiveMQConnectionFactory) connectionFactory).setTrustAllPackages(true);
        //2、使用连接工厂创建一个连接对象
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            //3、开启连接
            connection.start();
            //4、使用连接对象创建会话（session）对象
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
            Queue queue = session.createQueue("cnUser-queue");
            //6、使用会话对象创建生产者对象
            MessageConsumer consumer = session.createConsumer(queue);

            ActiveMQObjectMessage activeMQObjectMessage  = (ActiveMQObjectMessage)consumer.receive(1000);
            if (activeMQObjectMessage!=null){
                cnUser = (cnUser) activeMQObjectMessage.getObject();
            }
            //9、关闭资源
            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            RecordToFile.record(new String[]{"获取MQ消息异常了",e.getMessage()}, "error-log.txt",true);
            e.printStackTrace();
        }
        return cnUser;
    }
}
