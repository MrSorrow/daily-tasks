package bbgg.daily.task.api.fund.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FundBaseResult<T, U> extends BaseResult<T, U> {
    private boolean Success;
    private String Message;
    private String ErrorCode;
    private String ErrorMessage;
    private String ErrorMsgLst;
}