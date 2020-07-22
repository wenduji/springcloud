package com.example.study.jwt.entity;

import com.auth0.jwt.interfaces.Claim;
import com.example.study.test.entity.Person;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author hjs
 * @date 2020/7/17
 * @description
 */
@Getter
@Setter
public class JWTEntity {
    public static final String ACCESS_TOKEN_SUBJECT = "accessToken";
    public static final String REFRESH_TOKEN_SUBJECT = "refreshToken";
    public static final int ACCESS_TOKEN_EXPIRE_SECONDS = 3600 * 6;
    public static final int REFRESH_TOKEN_EXPIRE_SECONDS = 3600 * 48;
    public static final String DEFAULT_SECRET = "jwt-secret";

    private String userId;
    private String tokenSubject;
    private int tokenExpire;
    private Date tokenStartDate; //生效时间
    private Date tokenEndDate;   //失效时间
    private String secret;
    private Map<String, Claim> jwtClaimMap;

    //模拟签发者
    private String issuer;
    public static final String MOCK_ISSUER = "god";

    public JWTEntity(@NotNull String tokenSubject) {
        this.setTokenSubject(tokenSubject);
        this.secret = DEFAULT_SECRET;
    }

    public JWTEntity(@NotNull String tokenSubject, @NotNull Person person) {
        this.setTokenSubject(tokenSubject);
        this.userId = String.valueOf(person.getId());
        this.secret = JWTEntity.DEFAULT_SECRET;
    }

    public void setTokenSubject(@NotNull String tokenSubject) {
        this.tokenSubject = tokenSubject;
        if (ACCESS_TOKEN_SUBJECT.equals(tokenSubject)) {
            this.tokenExpire = ACCESS_TOKEN_EXPIRE_SECONDS;
        } else {
            this.tokenExpire = REFRESH_TOKEN_EXPIRE_SECONDS;
        }
        this.tokenStartDate = new Date();
        this.tokenEndDate = getExpireDate(this.tokenStartDate, this.tokenExpire);
    }

    public static Date getExpireDate(Date curDate, int seconds) {
        if (Objects.isNull(curDate)) {
            curDate = new Date();
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(curDate);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    public Map<String, Claim> getJwtClaimMap() {
        if(Objects.isNull(this.jwtClaimMap)) {
            this.jwtClaimMap = new LinkedHashMap<>();
        }
        return this.jwtClaimMap;
    }

    public void setJwtClaimMap(@NotNull Map<String, Claim> jwtClaimMap) {
        this.jwtClaimMap = jwtClaimMap;
    }

}
