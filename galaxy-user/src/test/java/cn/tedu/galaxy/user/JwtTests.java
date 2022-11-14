package cn.tedu.galaxy.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTests {

    // Secret Key
    String secretKey = "97iuFDVDfv97iuk534Tht3KJR89kBGFSBgfds";

    @Test
    public void testGenerate() {
        // 准备Claims值
        Map<String, Object> claims = new HashMap<>();
        claims.put("i", 1); // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpIjoxLCJleHAiOjE2NjI2MDg0NzJ9.5-gLgDoxbXvJ1FOcnH2pRS9CwHrrwPOdAJeIR6PSqeA

        // JWT的过期时间
        Date expiration = new Date(System.currentTimeMillis() + 5 * 60 * 10000);
        System.out.println("过期时间：" + expiration);

        // JWT的组成：Header（头：算法和Token类型）、Payload（载荷）、Signature（签名）
        String jwt = Jwts.builder()
                // Header
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // Payload
                .setClaims(claims)
                .setExpiration(expiration)
                // Signature
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        System.out.println("JWT=" + jwt);

        // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiTGl1TGFvU2hpIiwiaWQiOjk1MjcsImV4cCI6MTY2MjQ1NDg5NH0.mHYjK70qenmqmQ5_NrjZsh2P0t-QPKvBedVDRqH2ed8
        // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiTGl1TGFvU2hpIiwiaWQiOjk1MjcsImV4cCI6MTY2MjQ1NTA0NH0._7o_k9s3we-Ti-9rO4FpYzWxPxNDTFaLbAjZz-bOa8M

        // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
        // .
        // eyJuYW1lIjoiTGl1TGFvU2hpIiwibmlja25hbWUiOiJKYXZhQ2FuZ0xhb1NoaSIsImlkIjo5NTI3LCJleHAiOjE2NjI0NTUwOTV9
        // .
        // KaiBd1LskHVPZzwfDdeoZOCHQ4FB-P_69at0g-1jyqs
    }

    @Test
    public void testParse() {
        // 注意：必须使用相同secretKey生成的JWT，否则会解析失败
        // 注意：不可以使用过期的JWT，否则会解析失败
        // 注意：复制粘贴此JWT时，不要带“尾巴”，否则会解析失败
        // 注意：不可以恶意修改JWT中的任何字符，否则会解析失败
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpIjoxLCJleHAiOjE2NjUwNTcxOTN9.ZwYYoMdvIXlHeqxLudty1BASGU8xCrkOc19L3-yxUV0";

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
//        Integer id = claims.get("id", Integer.class);
//        String name = claims.get("name", String.class);
//        String nickname = claims.get("nickname", String.class);
//        System.out.println("id = " + id);
//        System.out.println("name = " + name);
//        System.out.println("nickname = " + nickname);

        Long id = claims.get("id", Long.class);
        System.out.println("id = " + id);

        String username = claims.get("username", String.class);
        System.out.println("username = " + username);
    }

}
