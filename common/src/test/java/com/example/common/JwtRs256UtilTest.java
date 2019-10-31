package com.example.common;

import com.example.common.define.ConstVal;
import com.example.common.entity.jwt.DefaultJwtPayload;
import com.example.common.entity.jwt.JwtPayload;
import com.example.common.util.JSON;
import com.example.common.util.jwt.JwtRs256Util;
import org.junit.Test;

public class JwtRs256UtilTest {
    /**
     * jwt创建token及token解析
     */
    @Test
    public void tokenTest() {
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCaxckIeXuBIaRJvubsx2yya8Xz\n" +
                "a+XLuG2TEfbIEpnIf4g3HL19v70tLTjMO58WzDed+N9K7WXb56Xvpgv8829EwjznKGq1UCrvVJ3M\n" +
                "IgDejUnlXZaYxkR0sGF7cCm8MqTlVJLUayVxC5oCaKKQ9df8m6Yh6bGN7xdbBe0T15qWkZmCT7Gc\n" +
                "2lApM7qM7NRMS6HPxi0IEDoiLWJYbqHXguMFreenDSnsBF6X2jjKgem+URsOOcFkA/VeKjfPyTE+\n" +
                "mNlW88vq5klvEBVoJWLXlhUvyzJipS/Kky5sl1c773JdLBJFZd/3HH9P/thC9kl30k6I+y3jxnzx\n" +
                "bTRCtWe6R0wZAgMBAAECggEASVNVF/++BIK0u/+Glj0xASLQKLBAsZCR0TCck1NEqlnJZhrmp5HU\n" +
                "hXe8Rf4lM/7ShrQmze/E5zdLRUuMuG14lMHVasqaK4ZsoPCcxd6CPO4BSXpaY8zjF8mMlZehHjl8\n" +
                "J+42lg6hMXqGn4eFJVpj1pjDT6PQ6+aPUR9wn4rxAU4AtuWUFtrdn+64wgEp62x5nKHDGt77peKO\n" +
                "lHaCxq5xJ6NhTzis7Ow69BYVYT32eiN8ulkcMlNXeEt4sjWQuntJfL+csvufwCf6hDk+Ti622/Cn\n" +
                "XbAv4r6s1Mpgly2GfRdxC5hbyAB4VMmQVl/aQyMzO/xP8R9KMYVDexE12JjJhQKBgQDpzHpAu4d5\n" +
                "JGuYVe09ekkQGK1+SbAW1XD5viLgRpe0VpVtVQ4FnmMmixNI+dnOltmbpLL+a4cDpVGCMvVIcX8s\n" +
                "SmHEJ9Lz6+LdOmEwZ7xBx5xfYMfWYga6t3Nx5f7vIrRSDTkIc4+a67bisD84JSSEIXAUVdYJtGSt\n" +
                "HL/LEOFhQwKBgQCpeDl2J0ZaXRuiNoVVGkQoe6WK+s+XBzIYJ+D9PfavftVBqeneUIGIyFwJPXSN\n" +
                "XQ9Oa+1TR6To2nhh/n8LNRHtB78LZKaXrjwDhB5cPnRpXLnwradQmFLwKZl92QSf/5to1H1JGC51\n" +
                "hMaPipV9GAc0d48XTMsY+S5hf60Y/XnJcwKBgG7cisvPIHoNDwf5FrmkFqkPSpUrk9f/9cY2por6\n" +
                "bk0REWH6ht+bLyDDqa1c7C7N0PnAqy+BBVBxP6khSLXFO6xgyOMejyUioTyNxjARwG7gnAGN/rRl\n" +
                "XxOCkbdce/og1qhhpL2hLmMjimELea7BzgLpaB/8Y7XOWD7N9xPvOS/JAoGALKe+VHDy69q+7lZ5\n" +
                "ebxLG3PBYkbGrqA5xf20HmbWetXr8bqJIoiKKXXKMrEb5igQYXS0+43UWXs/32qFJXndeFzjLWhf\n" +
                "MXa6355PtbTLTfweDtpjTSxmJlx/0pZ9zn82/z+Gp90UWOkcvTQiQe0z8NJURSp6FeGLJCvVe9FG\n" +
                "5xsCgYEAuVHQMxgQb1UkdGQQjxaJqW/xd8IHIBV4jRHKEq8BjqnpayhyEOY+C9E7NvrRvx63JyWd\n" +
                "v2+fPSFbJTedf9U6s64rnaJIKL/9gUqqJeSjZr+KGux68xrzzqsBSMhJGiyHoWNAJ6DiVPPgn0n7\n" +
                "lrFvGL1XIYaoSc6Oe1ZfmLowSrs=";

        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmsXJCHl7gSGkSb7m7MdssmvF82vly7ht\n" +
                "kxH2yBKZyH+INxy9fb+9LS04zDufFsw3nfjfSu1l2+el76YL/PNvRMI85yhqtVAq71SdzCIA3o1J\n" +
                "5V2WmMZEdLBhe3ApvDKk5VSS1GslcQuaAmiikPXX/JumIemxje8XWwXtE9ealpGZgk+xnNpQKTO6\n" +
                "jOzUTEuhz8YtCBA6Ii1iWG6h14LjBa3npw0p7ARel9o4yoHpvlEbDjnBZAP1Xio3z8kxPpjZVvPL\n" +
                "6uZJbxAVaCVi15YVL8syYqUvypMubJdXO+9yXSwSRWXf9xx/T/7YQvZJd9JOiPst48Z88W00QrVn\n" +
                "ukdMGQIDAQAB";

        JwtPayload payload = new DefaultJwtPayload();
        payload.put("user", "xujw");

        JwtRs256Util.createJWT(privateKey, payload, ConstVal.Token.EXPIRES).ifPresent(item -> {
            System.out.println("token: " + item);
            System.out.println("token内容：" + JSON.stringify(JwtRs256Util.parseJWT(item, publicKey).get()));
        });
    }
}
