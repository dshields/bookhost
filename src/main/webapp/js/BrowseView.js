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

function makeBrowseView()
{
  if(artistTable) return;
  
    dojo.inherits(SongTable,AmplitudeTable);
    dojo.lang.extend(SongTable, 
    {
        title:"Songs",
        divId:"songs",
        id:"songGrid",
        rowAttributes:function(rowIndex,row) {
          var rowClass = (rowIndex%2) ? "evenrow" : "oddrow";
          var attrs = {
             "class":rowClass,
             onMouseOut: "this.className='" + rowClass + " songLink';",
             onMouseOver: "this.className = 'highlight" + " songLink';",
             onclick:  "PlaySong(" + row.id +");"
          };
          return attrs;
        }
        
     });
    dojo.inherits(AlbumTable,AmplitudeTable);
    dojo.lang.extend(AlbumTable, {
        title:"Albums",
        divId:"albums",
        id:"albumGrid",
        rowAttributes:function(rowIndex,row) {
        var rowClass = (rowIndex%2) ? "evenrow" : "oddrow";
        var attrs = {
           "class":rowClass,
           onMouseOut: "this.className='" + rowClass + "';",
           onMouseOver: "this.className = 'highlight';",
           onclick:  "RequestAlbum(" + row.id +");",
           ondblclick: "PlayAlbum("+row.id + ");"
        };
        return attrs;
        }
    
    });
    
    dojo.inherits(ArtistTable,AmplitudeTable);
    dojo.lang.extend(ArtistTable,
          {
              title:"Artists",
              divId:"artists",
              id:"artistGrid",
              rowAttributes:function(rowIndex,row) {
                    var rowClass = (rowIndex%2) ? "evenrow" : "oddrow";
                    var attrs = {
                       "class":rowClass,
                       onMouseOut: "this.className='" + rowClass + "';",
                       onMouseOver: "this.className = 'highlight';",
                       onclick:  "RequestArtist(" + row.id +");",
                       ondblclick: "PlayArtist("+row.id + ");"
                    };
                    return attrs;
                 }
                /*rowAttributes:function(rowIndex,row) {
                    var attrs = {
                       onMouseOut: function() { this.className = ''; },
                       onMouseOver: function() { this.className = 'highlight'; },
                       onclick: createCallback(RequestArtist,row.id),
                       ondblclick: createCallback(PlayArtist,row.id)
                    });
                    return attrs;
                 }*/
          } 
    );
    
    artistTable = new ArtistTable();
    albumTable = new AlbumTable();
    songTable = new SongTable();
  
}

function loadBrowseView(artists)
{
      artistTable.cleanup();
      albumTable.cleanup();
      songTable.cleanup();
    
      dojo.lang.forEach(artists,function(artist)
          {
              var albums = artist.albums;
              albumTable.AddAlbums(albums);
              var songCount  = 0;
              for(var j = 0;  j < albums.length; j++)
              {
                songCount = songCount + albums[j].songs.length;
                songTable.AddSongs(albums[j].songs);         
              }
              artistTable.AddArtist(artist.id,artist.artistname,albums.length,songCount);
          }
       );    
    
      artistTable.createWidget();
      albumTable.createWidget();
      songTable.createWidget();

}

AlbumTable = function()
{
    this.addStringColumn("artistname","Artist");
    this.addStringColumn("albumname","Album");
    this.addNumberColumn("trackcount","Tracks");
    this.addNumberColumn("year","Year");
    this.AddAlbums = AddAlbums;
    
    function AddAlbums(albums)
    {
      this.rows = this.rows.concat(albums);
    }    
}

SongTable = function() 
{
    this.addNumberColumn("number","#");
    this.addStringColumn("name","Song Name");
    this.addStringColumn("artist","Artist");
    this.addStringColumn("album","Album");
    this.addNumberColumn("length","Length");
    this.addStringColumn("genre","Genre");
    this.AddSongs = AddSongs;
    
    function AddSongs(songs)
    {
        this.rows = this.rows.concat(songs);
    }

}

ArtistTable = function() 
{
    this.addNumberColumn("Tracks","#");
    this.addStringColumn("Artist","Artist");
    this.addNumberColumn("Albums","Albums");
    this.AddArtist = AddArtist;
    
    function AddArtist(id,name,albumcount,songcount) 
    {
      this.rows.push({id:id,Artist:name,Albums:albumcount,Tracks:songcount});
    }

};
