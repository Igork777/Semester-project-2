package server.manager;

import shared.wrappers.Band;

import java.util.ArrayList;

public interface SearchBandManager
{
	/**
	 * This method checks what fields of the band object are null and which are not,
	 * and based on that will call on the proper query from the database which will
	 * extract information based on the fields that are specified.
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	ArrayList<Band> search(Band band);
}
