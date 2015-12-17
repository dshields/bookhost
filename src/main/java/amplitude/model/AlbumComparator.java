package amplitude.model;

import java.util.Comparator;

class AlbumComparator implements Comparator<IAlbum> {

    public int compare(IAlbum one, IAlbum two) {
        String compare1Name = one.getArtist().getArtistName() + "_" + one.getAlbumName();
        String compare2Name = two.getArtist().getArtistName() + "_" + two.getAlbumName();

        return compare1Name.compareToIgnoreCase(compare2Name);
    }

}