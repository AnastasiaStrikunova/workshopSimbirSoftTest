import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

public class StatisticsTest {
    private Statistics statistics;
    @Before
    public void setStatistics(){
        statistics = new Statistics();
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("one", 1);
        linkedHashMap.put("two", 2);
        linkedHashMap.put("three", 3);
        statistics.setMap(linkedHashMap);
    }
    @Test
    public void addOn(){
        Statistics statistics1 = new Statistics();
        statistics1.addRegisterOn("one");
        statistics1.addRegisterOn("two");
        statistics1.addRegisterOn("two");
        statistics1.addRegisterOn("three");
        statistics1.addRegisterOn("three");
        statistics1.addRegisterOn("three");

        Assert.assertEquals(statistics.getMap(), statistics1.getMap());
    }

    @Test
    public void addOff(){
        Statistics statistics1 = new Statistics();
        statistics1.addRegisterOff("One");
        statistics1.addRegisterOff("tWo");
        statistics1.addRegisterOff("two");
        statistics1.addRegisterOff("THREE");
        statistics1.addRegisterOff("three");
        statistics1.addRegisterOff("three");

        Assert.assertEquals(statistics.getMap(), statistics1.getMap());
    }
}
