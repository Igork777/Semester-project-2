package client.model;

import client.networking.SearchBandClient;
import shared.wrappers.Band;

import java.util.ArrayList;
import java.util.List;

public class SearchBandModelImpl implements  SearchBandModel{

	private SearchBandClient searchBandClient;

	public SearchBandModelImpl(SearchBandClient searchBandClient)
	{
		this.searchBandClient = searchBandClient;
	}

	@Override public List<Band> search(Band band)
	{
		return searchBandClient.search(band);
	}


}
