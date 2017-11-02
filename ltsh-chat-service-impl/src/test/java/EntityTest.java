import org.junit.Test;
import org.ltsh.chat.service.entity.*;
import org.ltsh.chat.service.req.message.MessageGetServiceReq;
import org.ltsh.common.entity.UserToken;
import org.ltsh.common.utils.AesUtils;
import org.ltsh.common.utils.MD5Util;
import org.ltsh.common.utils.SignUtils;
import org.ltsh.common.utils.db.DbUtils;
import org.springframework.beans.BeanUtils;

/**
 * Created by Random on 2017/10/10.
 */
public class EntityTest {
    @Test
    public void testCr() {
//        String createTable = DbUtils.getCreateTable(DbUtils.getDbColumns(SysUserRole.class));
//        System.out.println(createTable);
//        createTable = DbUtils.getCreateTable(DbUtils.getDbColumns(UserInfo.class));
//        System.out.println(createTable);
//        createTable = DbUtils.getCreateTable(DbUtils.getDbColumns(SysUserBo.class));
//        System.out.println(createTable);
//        createTable = DbUtils.getCreateTable(DbUtils.getDbColumns(MessageEntity.class));
//        System.out.println(createTable);
    }
    @Test
    public void test123() {
        MessageGetServiceReq req = new MessageGetServiceReq();
        req.setKeep("213");
        UserToken userToken = new UserToken(1,"f","f", "123", "123");
        req.setToken(userToken.getToken());
        req.setUserToken(userToken);
        MessageGetServiceReq req1 = new MessageGetServiceReq();
        BeanUtils.copyProperties(req, req1);
        System.out.println(req1);
    }
    @Test
    public void test() throws Exception {
        String encoder = MD5Util.encoder("11212");
        System.out.println(encoder);
        String sign = SignUtils.getSign("123456", "123456", "");
        System.out.println(sign);
        String encrypt = AesUtils.encrypt("123456", "test");
        String decrypt = AesUtils.decrypt("5a0e45db5cf048be93d5eeda306ad274", "02D762D5F9480251D8EB119991FC86D4");
        System.out.println(new String(decrypt));
    }
}
