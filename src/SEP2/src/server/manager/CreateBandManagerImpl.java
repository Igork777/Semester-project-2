package server.manager;

import server.database.CreateBandDBAccess;
import server.database.CreateBandDBAccessImpl;
import shared.wrappers.Band;

public class CreateBandManagerImpl implements CreateBandManager {
    private CreateBandDBAccess createBandDBAccess;

    public CreateBandManagerImpl() {
        createBandDBAccess = new CreateBandDBAccessImpl();
    }

    @Override
    public String create(Band band) {
        return createBandDBAccess.create(band);
    }


}
