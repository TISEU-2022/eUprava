package ftn.euprava.mupvozila.util.jwt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Component
public class JwtTokenUtil {


    public boolean tokenVerified(String token) {
        //final String uri = "http://auth-app:5101/auth/verify_token/"+token;
        final String uri = "http://host.docker.internal:5101/auth/verify_token/"+token;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(uri)
                .addHeader("Authorization","Bearer " + token)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String getRoles(String token) {
        //------------ Decode JWT ------------
        String[] split_string = token.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        String base64EncodedSignature = split_string[2];

        //~~~~~~~~~ JWT Header ~~~~~~~
        Base64 base64Url = new Base64(true);

        System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
        String body = new String(base64Url.decode(base64EncodedBody));
        System.out.println("JWT Body : "+body);


        body = body.substring(1, body.length()-1);                //remove curly brackets
        LinkedList<String> myList = new LinkedList<>(Arrays.asList(body.split(",")));
        String roles = "";
        for(String pair : myList)                          //iterate over the pairs
        {
            if (pair.contains("roles")){
                String rolesString = pair.split(":")[1];
                rolesString = rolesString.substring(1, rolesString.length()-1); //remove brackets
                roles = rolesString;
                break;
            }
        }

        System.out.println("Returned roles: "+roles);
        return roles;
    }

    public String getUserId(String token) {
        //------------ Decode JWT ------------
        String[] split_string = token.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        String base64EncodedSignature = split_string[2];

        //~~~~~~~~~ JWT Header ~~~~~~~
        Base64 base64Url = new Base64(true);

        System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
        String body = new String(base64Url.decode(base64EncodedBody));
        System.out.println("JWT Body : "+body);


        body = body.substring(1, body.length()-1);                //remove curly brackets
        LinkedList<String> myList = new LinkedList<String>(Arrays.asList(body.split(",")));
        String userId = null;
        for(String pair : myList)                          //iterate over the pairs
        {
            if (pair.contains("sub")){
                userId = pair.split(":")[1];
                userId = userId.substring(1, userId.length()-1);
                break;
            }
        }

        System.out.println("Returned user id: "+userId);
        return userId;
    }
}
