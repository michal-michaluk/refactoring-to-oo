package acl;

import dao.ShortageDao;
import shortage.forecasting.Shortages;
import shortage.forecasting.ShortagesRepository;

public class ShortagesLegacyDBRepository implements ShortagesRepository {
    private ShortageDao shortageDao;

    @Override
    public Shortages get(String productRefNo) {
        return new Shortages(productRefNo, shortageDao.getForProduct(productRefNo));
    }
}
