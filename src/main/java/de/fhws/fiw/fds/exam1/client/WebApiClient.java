package de.fhws.fiw.fds.exam1.client;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import de.fhws.fiw.fds.exam1.models.Project;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class WebApiClient {
    private final Genson genson;
    private final OkHttpClient client;
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse( "application/json" );
    private static final String URL = "http://localhost:8080/exam1/api/projects";

    public WebApiClient()
    {
        this.client = new OkHttpClient();
        this.genson = new Genson();
    }

    public WebApiResponse loadById( final long id ) throws IOException
    {
        final String theUrl = String.format( "%s/%d", URL, id );
        final Response response = sendRequest( theUrl );
        if(response.code() != 200)
        {
            return new WebApiResponse(response.code());
        }
        else
        {
            return new WebApiResponse(deserializeToProject(response), response.code());
        }
        }

    public WebApiResponse loadAllProjects( ) throws IOException
    {
        return loadAllProjectsByNameANDSemesterANDType( "", "", "" );
    }

    public WebApiResponse loadAllProjectsByName( final String name ) throws IOException
    {
        return loadAllProjectsByNameANDSemesterANDType( name, "", "" );
    }

    public WebApiResponse loadAllProjectsBySemester( final String semester ) throws IOException
    {
        return loadAllProjectsByNameANDSemesterANDType( "", "", semester );
    }

    public WebApiResponse loadAllProjectsByType( final String type ) throws IOException
    {
        return loadAllProjectsByNameANDSemesterANDType( "", type,"" );
    }

    public WebApiResponse loadAllProjectsByNameANDSemesterANDType( final String name, final String type, final String semester ) throws IOException
    {
        final String theUrl = String.format( "%s?name=%s&type=%s&semester=%s", URL, name, type, semester );
        final Response response = sendRequest( theUrl );

        if(response.code() != 200)
        {
            return new WebApiResponse(response.code());
        }
        else
        {
            return new WebApiResponse( deserializeToProjectCollection(response), response.code( ) );
        }
    }

    private Response sendRequest( final String url ) throws IOException
    {
        final Request request = new Request.Builder( )
                .url( url )
                .get( )
                .build( );

        return this.client.newCall( request ).execute( );
    }

    private Response sendPost ( final String url, RequestBody body ) throws IOException
    {
        final Request request = new Request.Builder( )
                .url( url )
                .post( body)
                .build( );

        return this.client.newCall( request ).execute( );
    }

    public WebApiResponse postProject(ProjectView project) throws IOException
        {
            String url = String.format("%s", URL);
            String json = genson.serialize(project);
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
            Response response = sendPost(url, body);
            if(response.code() != 201)
            {
                return new WebApiResponse(response.code());
            }
            else
            {
                return new WebApiResponse(response.header("Location").toString(), response.code());
            }
        }


    public WebApiResponse updateProject(final long id, ProjectView project) throws IOException {
        String url = String.format("%s/%d", URL , id);
        String json = genson.serialize(project);
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Response response = sendPut(url, body);
        return new WebApiResponse(response.code());
    }

    private Response sendPut(final String url, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .put(body)
                .url(url)
                .build();

        return this.client.newCall(request).execute();
    }

    public WebApiResponse deleteProjectById(final long id) throws IOException {
            String url = String.format("%s/%d", URL, id);
            Response response = sendDelete(url);

            return new WebApiResponse(response.code());
    }

    private Response sendDelete(final String url) throws IOException {
        Request request = new Request.Builder()
                .delete()
                .url(url)
                .build();

      return client.newCall(request).execute();
    }

    private Collection<ProjectView> deserializeToProjectCollection(final Response response ) throws IOException
    {
        final String data = response.body( ).string( );
        return genson.deserialize( data, new GenericType<List<ProjectView>>( )
        {
        } );
    }

    private Optional<ProjectView> deserializeToProject(final Response response ) throws IOException
    {
        final String data = response.body( ).string( );
        return Optional.ofNullable( genson.deserialize( data, ProjectView.class ) );
    }


}
