package server.database;

import shared.wrappers.Band;

import java.util.ArrayList;

public interface SearchBandDBAccess
{
	/**
	 * Method containing a query which is used when search only the "Name of the band" search condition is specified
	 * @param name name of the band
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	ArrayList<Band> nameQuery(String name);

	/**
	 * Method containing a query which is used when search only the "Region" search condition is specified
	 * @param region region the band is located in
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	ArrayList<Band> regionQuery(String region);

	/**
	 * Method containing a query which is used when search only the "Genre" search condition is specified
	 * @param genreId the id of genre relating to the name of the genre in the database
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	ArrayList<Band> genreQuery(int genreId);

	/**
	 * Method containing a query which is used when search only the "Name of the band" and "Region" search condition are specified
	 * @param name name of the band
	 * @param region region the band is located in
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	ArrayList<Band> nameRegionQuery(String name, String region);

	/**
	 * Method containing a query which is used when search only the "Name of the band" and "Genre" search condition are specified
	 * @param name name of the band
	 * @param genreId the id of genre relating to the name of the genre in the database
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	ArrayList<Band> nameGenreQuery(String name, int genreId);

	/**
	 * Method containing a query which is used when search only the "Name of the band" and "Genre" search condition are specified
	 * @param region region the band is located in
	 * @param genreId the id of genre relating to the name of the genre in the database
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	ArrayList<Band> genreRegionQuery(int genreId, String region);

	/**
	 * Method containing a query which is used when all ("Name of the band", "Region", "Genre") the search condition are specified
	 * @param region region the band is located in
	 * @param genreId the id of genre relating to the name of the genre in the database
	 * @param name name of the band
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	ArrayList<Band> nameRegionGenreQuery(String name, String region, int genreId);

	/**
	 * Method containing a query which is used when none of the search conditions are specified
	 * @return ArrayList of all the Band objects in the database. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	ArrayList<Band> noSearchConditionQuery();
}
