package amplitude.model;

import java.util.Comparator;

class SongComparator implements Comparator<ISong> {

    public int compare(ISong song1, ISong song2) {
        String compare1Name = String.format("%s_%s-%2d", song1.getArtistName(), song1.getAlbumName(), song1.getTrackNumber());
        String compare2Name = String.format("%s_%s-%2d", song2.getArtistName(), song2.getAlbumName(), song2.getTrackNumber());

        return compare1Name.compareToIgnoreCase(compare2Name);
    }

}