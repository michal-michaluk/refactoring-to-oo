package shortage.forecasting;

import entities.ShortageEntity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Shortages {
    private final String productRefNo;
    private final List<ShortageEntity> shortages;

    public Shortages(String productRefNo) {
        this.productRefNo = productRefNo;
        shortages = new LinkedList<>();
    }

    public Shortages(String productRefNo, List<ShortageEntity> shortages) {
        this.productRefNo = productRefNo;
        this.shortages = shortages;
    }

    public void add(LocalDate day, long missing) {
        ShortageEntity entity = new ShortageEntity();
        entity.setRefNo(productRefNo);
        entity.setFound(LocalDate.now());
        entity.setAtDay(day);
        entity.setMissing(Math.abs(missing));
        shortages.add(entity);
    }

    public List<ShortageEntity> toList() {
        return shortages;
    }

    public boolean differentThen(Shortages other) {
        return !shortages.isEmpty() && !shortages.equals(other.shortages);
    }

    public boolean isSolved(Shortages previous) {
        return shortages.isEmpty() && !previous.shortages.isEmpty();
    }

    public boolean firstBefore(LocalDate date) {
        return shortages.get(0).getAtDay().isBefore(date);
    }
}
