package de.fhws.fiw.fds.exam1;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class WebApiResponse
{
	private Collection<ProjectView> responseData;

	private final int lastStatusCode;

	private String header;

	public WebApiResponse( final int lastStatusCode )
	{
		this( Collections.EMPTY_LIST, lastStatusCode );
	}

	public WebApiResponse(final ProjectView responseData, final int lastStatusCode )
	{
		this( Optional.of( responseData ), lastStatusCode );
	}

	public WebApiResponse(final Optional<ProjectView> responseData, final int lastStatusCode )
	{
		this( convertToList( responseData ), lastStatusCode );
	}

	public WebApiResponse(final Collection<ProjectView> responseData, final int lastStatusCode )
	{
		this.responseData = responseData;
		this.lastStatusCode = lastStatusCode;
	}

	public WebApiResponse(final String header, final int lastStatusCode) {
		this.header = header;
		this.lastStatusCode = lastStatusCode;
	}

	public WebApiResponse(final Collection<ProjectView> responseData, final int lastStatusCode, final String header )
	{
		this.responseData = responseData;
		this.lastStatusCode = lastStatusCode;
		this.header = header;
	}

	public Collection<ProjectView> getResponseData( )
	{
		return responseData;
	}

	public int getLastStatusCode( )
	{
		return lastStatusCode;
	}

	private static Collection<ProjectView> convertToList(final Optional<ProjectView> project )
	{
		return project.isPresent( ) ? Collections.singletonList( project.get( ) ) : Collections.emptyList( );
	}

	public String getHeader() {
		return this.header;
	}

	public long getIdFromHeaderString()
	{
		return Long.parseLong(this.header.substring(this.header.lastIndexOf("/") + 1));
	}
}
