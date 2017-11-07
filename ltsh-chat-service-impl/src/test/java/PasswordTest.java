import org.junit.Test;
import org.ltsh.chat.service.utils.PasswordUtils;
import org.ltsh.common.util.security.MD5Util;


/**
 * Created by Random on 2017/10/24.
 */
public class PasswordTest {
    @Test
    public void password() {
        String encoder1 = MD5Util.encoder("ltshUser:" + "111111");
        System.out.println(PasswordUtils.createPassword(encoder1));
        String encoder = MD5Util.encoder("chat:" + MD5Util.encoder("ltshUser:"+"111111"));
        System.out.println(encoder);
//        MD5Util.encoder("ltshChat:" + MD5Util.encoder("chat:"+password.toString()) + content[1])
    }
}
