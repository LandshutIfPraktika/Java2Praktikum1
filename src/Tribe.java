/**
 * This class represents a Bongo Tribe.
 * <p>
 * <p>
 * There are three named tribes and one
 * tribe called WILD. It represents the
 * banished Bongos.
 *
 * @author Georg Held
 */
public enum Tribe {


    Hulla, Tammtamm, Tammtammtamm;
    private int members = 0;

    /**
     * Increases or decreases
     * the member members.
     *
     * @param a the amount to in/decrease
     */
    public void changeMember(int a) {
        members += a;
    }

    /**
     * Gets the actual members.
     *
     * @return Returns the momentary members as an int.
     */
    public int getMembers() {
        return this.members;
    }


    public String toString() {
        switch (this) {
            case Hulla:
                return "Hulla";
            case Tammtamm:
                return "Tammtamm";
            case Tammtammtamm:
                return "Tammtammtamm";
            default:
                return "wild";
        }
    }

}
