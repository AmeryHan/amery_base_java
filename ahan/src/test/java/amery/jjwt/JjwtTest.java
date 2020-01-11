package amery.jjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JjwtTest {

    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder().setId("888").setSubject("小白")
                .setIssuedAt(new Date())//设置签发时间
                .signWith(SignatureAlgorithm.HS256, "xiaocai");//设置签名秘钥
        System.out.println(builder.compact());
    }

    @Test
    public void parse() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1Nzg3MTY0NTJ9.m2qseaLuO7yYULvUB9lotZQ-ZCpFnCSfO_KgxMww1a8";
        Claims claims = Jwts.parser().setSigningKey("xiaocai").parseClaimsJws(token).getBody();
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("IssuedAt:" + claims.getIssuedAt());
    }

    @Test
    public void expire() {
        //为了方便测试，我们将过期时间设置为1分钟
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 1000 * 60;//过期时间为1分钟
        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "xiaocai")
                .setExpiration(new Date(exp));//设置过期时间
        System.out.println(builder.compact());
    }

    @Test
    public void parse_expire() {
        String compactJws = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1Nzg3MTY1NzQsImV4cCI6MTU3ODcxNjYzNH0.PZD6v78BL3baKIgrj24w5vE1Sbbjpw2YpGlmXddFd0I";
        Claims claims = Jwts.parser()
                .setSigningKey("xiaocai")
                .parseClaimsJws(compactJws).getBody();
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy‐MM‐dd hh:mm:ss");
        System.out.println("签发时间:" + sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:" + sdf.format(claims.getExpiration()));
        System.out.println("当前时间:" + sdf.format(new Date()));
    }
}
