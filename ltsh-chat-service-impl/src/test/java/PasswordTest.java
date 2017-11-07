import org.junit.Test;
import org.ltsh.common.util.security.MD5Util;


/**
 * Created by Random on 2017/10/24.
 */
public class PasswordTest {
    @Test
    public void password() {
        String encoder = MD5Util.encoder("chat:" + "111111");
        System.out.println(encoder);
//        MD5Util.encoder("ltshChat:" + MD5Util.encoder("chat:"+password.toString()) + content[1])
    }
}
