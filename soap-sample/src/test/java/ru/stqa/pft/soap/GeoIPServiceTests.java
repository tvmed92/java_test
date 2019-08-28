package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class GeoIPServiceTests {

    @Test
    public void testMyIP() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("94.230.161.70");
        assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>66</State></GeoIP>");
    }

    @Test(enabled = false)
    public void testInvalidIP() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("94.230.161.kk");
        assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>66</State></GeoIP>");
    }
}
