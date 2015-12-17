package amplitude.utils;

import amplitude.model.IAlbum;
import amplitude.model.IArtist;
import amplitude.model.ISong;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class JSONUtil {

    public static JSONArray createJSONArtistArray(List<? extends IArtist> artists) {
        JSONArray jsonArtists = new JSONArray();
        for (IArtist artist : artists) {
            jsonArtists.add(JSONUtil.createJSONArtist(artist));
        }
        JSONObject json = new JSONObject();
        json.put("artists", jsonArtists);
        return jsonArtists;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject createJSONArtist(IArtist artist) {
        JSONObject json = new JSONObject();
        json.put("artistname", artist.getArtistName());
        json.put("id", artist.getArtistId());
        JSONArray albums = new JSONArray();
        for (IAlbum album : artist.getAlbums()) {
            albums.add(createJSONAlbum(album));
        }
        json.put("albums", albums);
        return json;

    }

    @SuppressWarnings("unchecked")
    public static JSONObject createJSONAlbum(IAlbum album) {
        JSONObject jalbum = new JSONObject();
        jalbum.put("id", album.getAlbumId());
        jalbum.put("albumname", album.getAlbumName());
        jalbum.put("artistname", album.getArtist().getArtistName());
        jalbum.put("trackcount", album.getTrackCount());
        jalbum.put("year", album.getAlbumYear());
        JSONArray jSongArray = new JSONArray();
        for (ISong song : album.getSongs()) {
            jSongArray.add(createJSONSong(song));
        }
        jalbum.put("songs", jSongArray);
        return jalbum;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject createJSONSong(ISong song) {
        JSONObject jsong = new JSONObject();
        jsong.put("id", song.getSongId());
        jsong.put("number", song.getTrackNumber());
        jsong.put("name", song.getSongName());
        jsong.put("artist", song.getArtistName());
        jsong.put("album", song.getAlbumName());
        jsong.put("length", song.getLength());
        jsong.put("genre", song.getGenreName());
        return jsong;
    }

}
