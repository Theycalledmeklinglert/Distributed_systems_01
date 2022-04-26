package de.fhws.fiw.fds.exam1.client;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class Client {
    private static final Genson genson = new Genson( );
    private static final OkHttpClient client = new OkHttpClient( );
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse( "application/json" );
    private static final String URL = "http://localhost:8080/exam1/api/projects";

    public static void main(String[] args) throws Exception {
        Client ProjectClient = new Client();
        System.out.println(   ProjectClient.getSingleProject(URL+"/1").toString());
    }

    public Project getSingleProject(final String url) throws Exception {
            final Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            int status = response.code();

            if(status == 200) {
                String body = response.body().string();
                return genson.deserialize(body, Project.class);

            }
            else {
                throw new IllegalArgumentException();
            }
    }

    public List<Project> getProjectCollection(final String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        final int status = response.code();

        if(status == 200) {
            final String body = response.body().string();
            List<Project> list = genson.deserialize(body, new GenericType<List<Project>>( ) {} );
            return list;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String postProject(Project project) throws Exception {
        String json = genson.serialize(project);
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Request request = new Request.Builder()
                .post(body)
                .url(URL)
                .build();

        Response response = client.newCall(request).execute();
        final int status = response.code();

        if(status == 201) {
            return response.header("Location");
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void putProject(final String url, Project project) throws Exception {
        String json = genson.serialize(project);
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Request request = new Request.Builder()
                .put(body)
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        final int status = response.code();

        if(status != 204) {
            throw new IllegalArgumentException();
        }
    }

    public void deleteProject(final String url) throws Exception {
        Request request = new Request.Builder()
                .delete()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        final int status = response.code();
        if(status != 204) {
            throw new IllegalArgumentException();
        }
    }

}
