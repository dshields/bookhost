package amplitude.model;

import java.util.Comparator;

class ArtistComparator implements Comparator<IArtist> {

    public int compare(IArtist one, IArtist two) {
        String compare1Name = one.getArtistName();
        String compare2Name = two.getArtistName();

        return compare1Name.compareToIgnoreCase(compare2Name);
    }

}
