package motor.notifier;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

public class SMSUtil {

    public static void sendSMS() { 
        
        // TemplateId        65b3f994d6fc05612b7633c2 (got rejected)
        // TemplateId        65b40ba7d6fc0501b164c0b3  (got verified with manual testing)

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"template_id\":\"65b40ba7d6fc0501b164c0b3\",\"short_url\":\"0\",\"recipients\":[{\"mobiles\":\"917702969440\",\"devicenumber\":\"Dev1234\"}]}");

        Request request = new Request.Builder()
        .url("https://control.msg91.com/api/v5/flow/")
        .post(body)
        .addHeader("accept", "application/json")
        .addHeader("content-type", "application/json")
        .addHeader("authkey", "414759AGLz7orF65b40aeaP1")
        .build();

        System.out.println("about to send sms");
        try {
            Response execute = client.newCall(request).execute();
            System.out.println("Done" + execute.isSuccessful());
            System.out.println(execute.message());
        } catch(IOException e) {
            System.out.println("Caught exception");
            e.printStackTrace();
        }
    }

    

    private static void sendSMSViaGupshup(String message, String phoneNumber) {
        try {
            HttpClient client = HttpClients.createDefault();
            String url = "https://api.gupshup.io/sm/api/v1/msg"; // Use the correct GupShup API endpoint
            HttpPost post = new HttpPost(url);

            // Add headers
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setHeader("apikey", "c0dsgteud5esjklq17x0ibqqe4mrlyu1");

            // Add URL parameters
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("message", message));
            urlParameters.add(new BasicNameValuePair("phoneNumber", phoneNumber));
            // ... add other required parameters

            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            System.out.println("about to send sms");

            HttpResponse response = client.execute(post);
            System.out.println("Execution Done" + response.getStatusLine());
            System.out.println(" Reponse" + response.toString());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            System.out.println("Caught exception");
            e.printStackTrace();
        }
    }
}