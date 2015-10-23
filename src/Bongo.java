/**
 * This class implements the necessary logic for the BongoMain
 * programm.
 *
 * @author Georg Held
 */
public class Bongo {


    private static Bongo chief;

    private Tribe tribe;
    private int beauty = 1;

    /**
     * Implements a tribeless Bongo with
     * zero beauty.
     */
    public Bongo() {
        this.tribe = null;
        this.beauty = 0;
    }

    /**
     * Implements a new Bongo with beauty 1.
     */
    public Bongo(Tribe tribe) {
        this.tribe = tribe;

        this.tribe.changeMember(1);

    }

    /**
     * Get the momentary chief of the Bongos.
     *
     * @return reference tho the chief.
     */
    public static Bongo getChief() {
        return chief;
    }

    /**
     * Get the beauty of a Bongo.
     *
     * @return the beauty as an int.
     */
    public int getBeauty() {
        return this.beauty;
    }

    /**
     * Get the Tribe of a Bongo.
     *
     * @return reference to the Tribe.
     * @see Tribe
     */
    public Tribe getTribe() {
        return this.tribe;
    }

    /**
     * Increments the beauty of the Bongo.
     * <p>
     * <p>
     * If the beauty of the Bongo gets higher than the beauty
     * of the chief, it becomes the new chief.
     *
     * @return reference to this
     */
    public Bongo brezelUp() {
        this.beauty++;
        if (chief == null || this.beauty > chief.beauty && this.tribe != null) {
            chief = this;
        }
        return this;
    }

    /**
     * Simulates the fighting of two Bongos.
     * <p>
     * After the fight both Bongos have their beauty reduced by half.
     * If the beauty of one Bongo reachesd zero it gets banished.
     * <p>
     * If one of the involved Bongos is the chief, it loses the position.
     *
     * @param that the opposing Bongo
     * @return reference to this
     */
    public Bongo fetz(Bongo that) {
        if (this.tribe == that.tribe) {
            return this;
        } else {
            this.beauty = this.beauty / 2;
            that.beauty = that.beauty / 2;

            if (this.tribe != null && this.beauty == 0) {
                this.tribe.changeMember(-1);
                this.tribe = null;
            }
            if (that.tribe != null && that.beauty == 0) {
                that.tribe.changeMember(-1);
                that.tribe = null;
            }
            if (this == chief) {
                chief = null;
            }
            if (that == chief) {
                chief = null;
            }

            return this;
        }
    }

    /**
     * Gets the number of tribe kin.
     *
     * @return number of tribe kin as an int
     */
    public int getKins() {
        if (this.tribe != null) {
            return this.tribe.getMembers();
        } else return 0;
    }


    public String toString() {
        return "Bongo: tribe = " + this.getTribe() + ", beauty = " + this.getBeauty();
    }

}
