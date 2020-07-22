package client.model;

import shared.wrappers.Band;

import java.util.ArrayList;
import java.util.List;

public interface SearchBandModel
{

	List<Band> search(Band band);

}
