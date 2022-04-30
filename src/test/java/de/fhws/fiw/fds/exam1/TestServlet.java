/*
package de.fhws.fiw.fds.exam1;

import de.fhws.fiw.fds.exam1.client.WebApiClient;
import de.fhws.fiw.fds.exam1.client.WebApiResponse;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestServlet
{
	@Test
	public void load_existing_person_by_id_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		final WebApiResponse response = client.loadById( 1l );

		assertEquals( 200, response.getLastStatusCode( ) );
		assertEquals( 1, response.getResponseData( ).size( ) );

		final Optional<PersonView> result = response.getResponseData( ).stream( ).findFirst( );
		assertTrue( result.isPresent( ) );
		final PersonView person = result.get( );

		assertEquals( "Felix", person.getFirstName( ) );
		assertEquals( "Leiter", person.getLastName( ) );
		assertEquals( LocalDateTime.of( 1960, 2, 9, 0, 0 ), person.getBirthDate( ) );
	}

	@Test
	public void load_existing_person_by_id_status404( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		final WebApiResponse response = client.loadById( 2l );
		
		assertEquals( 404, response.getLastStatusCode( ) );
		assertEquals( 0, response.getResponseData( ).size( ) );
	}

	@Test
	public void load_existing_person_by_name_existent_status200( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		final WebApiResponse response = client.loadAllPersonsByFirstAndLastName("Felix", "Leiter");

		final Optional<PersonView> result = response.getResponseData( ).stream( ).findFirst( );
		assertTrue( result.isPresent( ) );
		final PersonView person = result.get( );

		assertEquals( 200, response.getLastStatusCode( ) );
		assertEquals(1, response.getResponseData().size());
		assertEquals( LocalDateTime.of( 1960, 2, 9, 0, 0 ), person.getBirthDate( ) );
	}

	@Test
	public void test_post_Person_status201( ) throws IOException
	{
		final WebApiClient client = new WebApiClient( );
		final PersonView person = new PersonView("Ernst", "Blofeld", LocalDateTime.now());
		final WebApiResponse response = client.postPerson(person);

		assertEquals( 201, response.getLastStatusCode( ) );
		final WebApiResponse loadResponse = client.loadAllPersonsByFirstName("Ernst");
		assertEquals( 1, loadResponse.getResponseData( ).size( ) );

		// TODO Delete Test environment after remove is implemented
		// TODO test if lastname and birthday is correct
	}

	// TODO add more methods to test all cases. You have to modify class PersonStorage and populate more test data.

}

 */
