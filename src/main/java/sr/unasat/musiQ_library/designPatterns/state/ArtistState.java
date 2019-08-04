package sr.unasat.musiQ_library.designPatterns.state;

public class ArtistState {

    public static final String SOLO = "Solo";
    public static final String DUO = "Duo";
    public static final String GROUP = "Group";
    public static final String BAND = "Band";

    private String artistType;

    public ArtistState(String artistType) {
        switch (artistType) {
            case SOLO:
                this.artistType = SOLO;
                break;
            case DUO:
                this.artistType = DUO;
                break;
            case GROUP:
                this.artistType = GROUP;
                break;
            case BAND:
                this.artistType = BAND;
                break;
            default:
                throw new NullPointerException();
        }
        members(artistType);
    }

    public ArtistState() {

    }

    public String getArtistType() {
        return artistType;
    }

    public void setArtistType(String artistType) {
        this.artistType = artistType;
    }

    private void members(String artistType) {
        if (artistType.equals(DUO)) {
            System.out.println("This duo has 2 members");
        } else if (artistType.equals(GROUP)) {
            System.out.println("This is a group of artists");
        } else if (artistType.equals(BAND)) {
            System.out.println("This is a band");
        } else {
            System.out.println("This is a solo artist");
        }
    }
}
