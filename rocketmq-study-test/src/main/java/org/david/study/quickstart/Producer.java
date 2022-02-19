package org.david.study.quickstart;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @author david
 */
public class Producer {
    public static void main(String[] args) throws InterruptedException {

        /*
          指定生产者组的构造函数。
          参数：
          producerGroup – 生产者组，见同名字段。
          rpcHook – 每次远程命令执行时执行的 RPC 挂钩。
          enableMsgTrace – 消息跟踪的切换标志实例。
          customTraceTopic - 消息跟踪主题的名称值。如果不配置，可以使用默认的跟踪主题名称。
         */
        DefaultMQProducer producer = new DefaultMQProducer("study-producer-test-group");
        producer.setNamesrvAddr("127.0.0.1:9876");

        try {

            producer.start();

            Message msg = new Message(
                    "quickstart",
                    "TagA",
                    "Hello world! ".getBytes(StandardCharsets.UTF_8));

            SendResult sendResult = producer.send(msg);

            System.out.printf("%s%n", sendResult);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
            Thread.sleep(1000);
        }

        producer.shutdown();
    }
}
