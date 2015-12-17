/**
 * @author dshields
 */
//example JSON
//artist = {
//             artistName: "name", 
//             albums:  
//             [ 
//               { 
//                 albumname: "albumName",
//                 artistname: "artistName",
//                 trackcount: "trackcount",
//                 year: "year",
//                 songs:
//                 [ 
//                   {
//                         id: "id",
//                         number: "track",
//                         name: "song name",
//                         artist:  "artistName",
//                         album: "albumname",
//                         length: "length",
//                         genre: "genre"
//                   }
//                 ]
//              }
//            ]};

PlayView = function()
{
  this.artists = [];
  this.divClass = "artistHolder";
  this.htmlText = "";
  this.load = load;
  this.cleanup = cleanup;
  this.addArtist = addArtist;
  this.addAlbums = addAlbums;
  this.addSongs = addSongs;
  HTMLBuilder.apply(this);
  
  function load(artists)
  {
      this.htmlText = "";
      for(var i = 0; i < artists.length; i++)
      {
        this.htmlText = this.htmlText + this.addArtist(artists[i])
      }
      
      dojo.byId("playView").innerHTML = this.htmlText;
  }
  
  function cleanup()
  {
    delete this.artists;
    this.artists = [];
  }

  function addArtist(artist)
  {      
      if(!artist || artist.id==-1) return "";
      var albumElements = this.addAlbums(artist.albums);
      return this.DIV({"class":"artistHolder"},
               this.DIV({"class":"artistName"},
                 this.A({"href":artist.id.toString(),"class":"artistLink"},artist.artistname)),
               this.addAlbums(artist.albums));
  }
  
  
  function addAlbums(albums)
  {
      var elements = [];
      for(var i = 0; i < albums.length; i++)
      {
          var album = albums[i];
          var element = this.DIV({"class":"albumHolder"},
                            this.DIV({"class":"albumName"},
                              this.A({"href":album.id.toString(),"class":"albumLink"},
                                       album.albumname)),
                              this.addSongs(album.songs));
          elements.push(element);
      }
      return this.DIV({},elements);
  }
  
  function addSongs(songs)
  {
      var elements = [];
      for(var i = 0; i < songs.length; i++)
      {
          var song = songs[i];
          var element = this.LI({},
                        this.A({"class":"songLink",
                                href:song.id.toString()},
                        song.number + " - " +song.name));
          elements.push(element);
      }
      return this.DIV({"class":"songs"},this.UL({"class":"songLinks"},elements));
  }
}