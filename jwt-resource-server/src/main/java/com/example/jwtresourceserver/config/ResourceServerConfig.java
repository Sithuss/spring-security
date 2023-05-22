package com.example.jwtresourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${alias}")
    private String alias;
    @Value("${password}")
    private String password;
    @Value("${privateKey}")
    private String privateKey;

//    @Value("${jwt.key}")
//    private String jwtKey;

    // curl -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODIxOTc0ODIsInVzZXJfbmFtZSI6ImpvaG4iLCJhdXRob3JpdGllcyI6WyJyZWFkIl0sImp0aSI6ImQ3NGU3NWIyLWRlMzEtNDc1Yi04YWNlLTE5MzZmMmE3OGE5MiIsImNsaWVudF9pZCI6ImNsaWVudCIsInNjb3BlIjpbInJlYWQiXX0.Y1PR8-SzdzT6CCVqooIl5Mvq3hlxPumYpA5CvlVe6RAJ_oIP5gDgCE7UXjCO-cKwo2gSPiOITsbPr0dPuVks-jw9IWuEru-7EUGhL5pKg4WvqJDIVBDJP8ZEcxNPE9Lpzyvtp8r_-TI68eS5tKNJ-qp0diFB_2L7G2wU5auXb6DErzqse_Sli6yPBqUP2lKgy9BM4YK_JjinXiVPwUsVHaht-0_aOSrqOeXseRe7idR8MAHiwc9uIrkgmBF4vJWdhXf8f63VUpRWKG02vbX5P3_KlpL9K3H8j-9pHUuhHt1lfdFRgsPC1WH8Yi2c7bOX5X8wM8Quc2xYAwW9RaW8-w" http://localhost:9000/hello

    // connect token ****
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        var converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(jwtKey);
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(privateKey),password.toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(alias));
        return converter;
    }

}
