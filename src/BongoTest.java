import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by s-gheldd on 10/13/15.
 */
public class BongoTest {
    /*enter name of static chief field in Bongo.java*/
    private static String nameOfChief = "chief";
    private static String nameOfMembers = "members";

    @Before
    public void resetChief() throws Exception{
        Field chief = Bongo.class.getDeclaredField(nameOfChief);
        chief.setAccessible(true);
        chief.set(null, null);

        Field members =Tribe.class.getDeclaredField(nameOfMembers);
        members.setAccessible(true);
        members.set(Tribe.Hulla,0);
        members.set(Tribe.Tammtamm,0);
        members.set(Tribe.Tammtammtamm,0);
    }

    @Test(timeout = 100)
    public void testConstructorStandard() throws Exception {
        Bongo bongoWild = new Bongo();
        Bongo bongoWild2 = new Bongo();
        int[] tribesCountExpected = {0,0,0};

        assertNotNull(bongoWild);
        assertEquals(bongoWild.getBeauty(), 0);
        assertEquals(bongoWild.getClass(), Bongo.class);
        assertNotSame(bongoWild, bongoWild2);
        assertNull(bongoWild.getTribe());
        assertArrayEquals(tribesCountExpected,new int[]{Tribe.Hulla.getMembers(),Tribe.Tammtamm.getMembers(),Tribe.Tammtammtamm.getMembers()});
    }

    @Test(timeout = 100)
    public void testConstructorCustom() throws Exception {
        Bongo bongoTamtam = new Bongo(Tribe.Tammtamm);
        Bongo bongoTamTam2 = new Bongo(Tribe.Tammtamm);
        Bongo bongoHula = new Bongo(Tribe.Hulla);
        Bongo bongoTamTamTam = new Bongo(Tribe.Tammtammtamm);
        int[] tribesCountExpected = {1,2,1};

        Tribe[] tribes = {bongoTamtam.getTribe(), bongoHula.getTribe(), bongoTamTamTam.getTribe()};
        Tribe[] tribesExpected = {Tribe.Tammtamm, Tribe.Hulla, Tribe.Tammtammtamm};
        int[] beauties = {bongoTamtam.getBeauty(), bongoHula.getBeauty(), bongoTamTamTam.getBeauty()};

        assertNotNull(bongoTamtam);
        assertNotNull(bongoHula);
        assertNotNull(bongoTamTamTam);

        assertNotSame(bongoTamtam, bongoTamTam2);

        assertArrayEquals(tribes, tribesExpected);
        assertArrayEquals(beauties, new int[]{1, 1, 1});
        assertArrayEquals(tribesCountExpected,new int[]{Tribe.Hulla.getMembers(),Tribe.Tammtamm.getMembers(),Tribe.Tammtammtamm.getMembers()});

    }

    @Test(timeout = 100)
    public void testGetChief() throws Exception {

        Bongo bongoTamTam = new Bongo(Tribe.Tammtamm);
        Bongo bongoWild = new Bongo();

        assertNull(Bongo.getChief());

        bongoWild.brezelUp();
        assertSame(Bongo.getChief(), bongoWild);

        bongoTamTam.brezelUp();
        assertSame(Bongo.getChief(), bongoTamTam);
    }




    @Test(timeout = 100)
    public void testBrezelUp() throws Exception {

        Bongo bongoHulla = new Bongo(Tribe.Hulla);
        Bongo bongoTamtam = new Bongo(Tribe.Tammtamm);
        Bongo bongoTamtamtam = new Bongo(Tribe.Tammtammtamm);
        Bongo bongoWild = new Bongo();

        int[] beautiesExpected = {1, 2, 2, 2};

        assertNull(Bongo.getChief());
        bongoWild.brezelUp();
        assertSame(Bongo.getChief(), bongoWild);
        bongoHulla.brezelUp();
        assertSame(Bongo.getChief(), bongoHulla);
        bongoTamtam.brezelUp();
        assertSame(Bongo.getChief(), bongoHulla);

        bongoTamtamtam.brezelUp();
        assertArrayEquals(new int[]{bongoWild.getBeauty(), bongoHulla.getBeauty(), bongoTamtam.getBeauty(),
                bongoTamtamtam.getBeauty()}, beautiesExpected);
    }

    @Test(timeout = 100)
    public void testFetz() throws Exception {
        Bongo bongoHulla = new Bongo(Tribe.Hulla);
        Bongo bongoTamtam = new Bongo(Tribe.Tammtamm);
        Bongo bongoTamtam2 = new Bongo(Tribe.Tammtamm);
        Bongo bongoTamtamtam = new Bongo(Tribe.Tammtammtamm);
        Bongo bongoWild = new Bongo();
        Bongo bongoWild2 = new Bongo();

        bongoWild.brezelUp();
        bongoWild.fetz(bongoWild2);
        assertArrayEquals(new int[]{1,0},new int[]{bongoWild.getBeauty(),bongoWild2.getBeauty()});

        bongoTamtam.brezelUp();
        bongoTamtam.fetz(bongoTamtam2);
        assertArrayEquals(new int[]{2,1},new int[]{bongoTamtam.getBeauty(),bongoTamtam2.getBeauty()});

        bongoTamtam.fetz(bongoHulla);
        assertNull(Bongo.getChief());
        assertArrayEquals(new int[]{1,0},new int[]{bongoTamtam.getBeauty(),bongoHulla.getBeauty()});

        assertEquals(1,bongoTamtamtam.getBeauty());

        assertArrayEquals(new int[]{0,2,1},new int[]{Tribe.Hulla.getMembers(),Tribe.Tammtamm.getMembers(),Tribe.Tammtammtamm.getMembers()});

        bongoTamtamtam.brezelUp();
        bongoWild.fetz(bongoTamtamtam);

        assertNull(Bongo.getChief());

        bongoTamtamtam.fetz(bongoWild);
        assertNull(bongoTamtamtam.getTribe());

    }

    @Test(timeout = 100)
    public void testGetKin() throws Exception {
        int[] tribesMembersExpected = {2,2,1,1,0};
        Bongo bongoHulla = new Bongo(Tribe.Hulla);
        Bongo bongoHulla2 = new Bongo(Tribe.Hulla);
        Bongo bongoTamTam = new Bongo(Tribe.Tammtamm);
        Bongo bongoTamTamTam = new Bongo(Tribe.Tammtammtamm);
        Bongo bongWild = new Bongo();
        assertArrayEquals(tribesMembersExpected,new int[]{bongoHulla.getKins(),bongoHulla2.getKins(),bongoTamTam.getKins(),bongoTamTamTam.getKins(),bongWild.getKins()});
    }


    @Test(timeout=100)
    public void testToString() throws Exception {
        assertEquals("Bongo: tribe = null, beauty = 0",new Bongo().toString());
        assertEquals("Bongo: tribe = Hulla, beauty = 1",new Bongo(Tribe.Hulla).toString());

    }
}