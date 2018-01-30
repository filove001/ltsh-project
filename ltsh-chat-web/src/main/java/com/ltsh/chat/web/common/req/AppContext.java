package com.ltsh.chat.web.common.req;

import com.ltsh.common.entity.RequestContext;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import com.ltsh.common.entity.UserToken;

import java.io.Serializable;

/**
 * Created by Random on 2017/10/9.
 */
@Data
public class AppContext<T> extends RequestContext<T> {

}
