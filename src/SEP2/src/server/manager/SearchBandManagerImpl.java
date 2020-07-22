package server.manager;

import server.database.SearchBandDBAccess;
import server.database.SearchBandDBImpl;
import shared.wrappers.Band;

import java.util.ArrayList;

public class SearchBandManagerImpl implements SearchBandManager
{
	private SearchBandDBAccess searchBandDBAccess;

	/**
	 * Constructor initializing the access to the search band database
	 */
	public SearchBandManagerImpl()
	{
		searchBandDBAccess = new SearchBandDBImpl();
	}

	/**
	 * This method checks what fields of the band object are null and which are not,
	 * and based on that will call on the proper query from the database which will
	 * extract information based on the fields that are specified.
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */

	@Override public ArrayList<Band> search(Band band)
	{
		String name = band.getNameOfTheBand();
		String region = band.getRegion();
		int genreId = band.getGenreId();

		if(name != null && region == null && genreId == 0)
		{
			return searchBandDBAccess.nameQuery(name);
		}
		else if(name != null && region != null && genreId == 0)
		{
			return searchBandDBAccess.nameRegionQuery(name, region);
		}
		else if (name != null && region != null && genreId != 0)
		{
			return searchBandDBAccess.nameRegionGenreQuery(name,region,genreId);
		}
		else if (name == null && region != null && genreId == 0)
		{
			return searchBandDBAccess.regionQuery(region);
		}
		else if (name == null && region != null && genreId != 0)
		{
			return searchBandDBAccess.genreRegionQuery(genreId, region);
		}
		else if (name != null && region == null && genreId != 0)
		{
			return searchBandDBAccess.nameGenreQuery(name,genreId);
		}
		else if (name == null && region == null && genreId != 0)
		{
			return searchBandDBAccess.genreQuery(genreId);
		}
		else
		{
			return searchBandDBAccess.noSearchConditionQuery();
		}
	}
}
