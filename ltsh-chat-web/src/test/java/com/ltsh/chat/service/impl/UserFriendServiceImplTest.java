package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserFriendService;
import com.ltsh.chat.service.entity.UserFriend;
import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.web.start.StartUp;
import com.ltsh.common.entity.UserToken;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fengjianbo on 2017/12/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartUp.class)
public class UserFriendServiceImplTest {
    @Autowired
    UserFriendService userFriendService;
//    @Test
    public void testPage() {
        PageReq<UserFriend> pageReq = new PageReq<UserFriend>();
        pageReq.setPageNumber(1L);
        pageReq.setPageSize(1000L);
        UserToken userToken = new UserToken(1, "test1", "测试用户1", "", "aa1f06787a25494596a93a4e2580935e");
        pageReq.setUserToken(userToken);
        userFriendService.page(pageReq);
    }
}
