package io.sansam.service.mq;


/**
 * <p>
 * IMessageService
 * </p>
 *
 * @author houcb
 * @since 2020-08-06 14:51
 */
public interface IMessageService {

    String sendDirect(String routingKey, String exchangeName, Object msg);

    String sendTopic(String routingKey, String exchangeName, Object msg);


}
