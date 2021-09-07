package bbgg.daily.task.push;

import bbgg.daily.task.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description:
 * @author: guoping
 * @date: 2021/6/18
 */
@Slf4j
@Component
public class WxPush {

    @Autowired
    private WxCpService wxCpService;

    public void pushTextMsgToParty(String msg, String party) {
        WxCpMessage message = WxCpMessage.TEXT().agentId(Constants.AGENT_ID).toParty(party).content(msg).build();

        try {
            wxCpService.getMessageService().send(message);
        } catch (WxErrorException e) {
            log.error("WxPush.pushTextMsg error, msg: {}, party: {}", msg, party, e);
        }
    }

    public void pushTextMsgToUser(String msg, String user) {
        WxCpMessage message = WxCpMessage.TEXT().agentId(Constants.AGENT_ID).toUser(user).content(msg).build();

        try {
            wxCpService.getMessageService().send(message);
        } catch (WxErrorException e) {
            log.error("WxPush.pushTextMsg error, msg: {}, user: {}", msg, user, e);
        }
    }

}
