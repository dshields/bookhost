
var baseURL = "";
var artistTable;
var albumTable;
var songTable;
var playView; 
var view = "playView"; 
var _artists;

function PlayArtist(id) {
    document.location = "select?type=artist&id=" + id;
}
function PlayAlbum(id) {
    	document.location = "select?type=album&id=" + id;
}
function PlaySong(id) {
    	//document.location = "select?type=song&id=" + id;
        document.location = "play?s=" + id;
}

function bindSearchForm()
{
    var searchForm = dojo.byId("searchForm");
    var x = new dojo.io.FormBind(
      {
        formNode: "searchForm",
        load: function(type, data, e)
        {
           loadSongs(type,eval(data),e);
        }
     }
    );

}

function init()
{
      
    dojo.byId("browse").onclick = function() { showBrowseView(); };
    dojo.byId("listen").onclick = function() { showPlayView(); };
    bindSearchForm();
    
    makePlayView();
    makeBrowseView();
    showPlayView();
    
    //While protyping the page:
    //LoadTestData();

    RequestArtist(-1);

}

function makePlayView()
{
  playView = new PlayView();
}

function showBrowseView()
{
    view="browseView";
    loadSongs(null,_artists,null);
    dojo.html.show("songWindow");
    dojo.html.show("artistWindow");
    dojo.html.show("albumWindow");
    dojo.html.hide("playView");
}

function showPlayView()
{
    view="playView";
    loadSongs(null,_artists,null);
    dojo.html.hide("songWindow");
    dojo.html.hide("artistWindow");
    dojo.html.hide("albumWindow");
    dojo.html.show("playView");    
}


function createCallback(f,arg)
{
  return function() { return f(arg); }; 
}

function handleAjaxFailure(type,data,event)
{
    alert("Unable to process request");
}
    
function loadSongs(type,artists,event)
{
  if(!artists) return;
  _artists = artists;
  if(view == "playView")
  {
      playView.cleanup();
      playView.load(artists);      
  } 
  else 
  {
      loadBrowseView(artists);
  }
  attachLinks();
  
  /*
    RUZEE.Borders.add(
    {
        "#artistWindow" : { borderType:"shadow", cornerRadius:5, shadowWidth:10, height:"230px" },
        "#albumWindow" : { borderType:"shadow", cornerRadius:5, shadowWidth:10, height:"230px" },
        "#songWindow" : { borderType:"shadow", cornerRadius:5, shadowWidth:10, height:"230px" },
        "#playlistWindow" : { borderType:"shadow", cornerRadius:5, shadowWidth:10, height:"200px" },
        ".artistHolder" : { borderType:"shadow", cornerRadius:5, shadowWidth:10 },
        ".albumHolder" : { borderType:"shadow", cornerRadius:5, shadowWidth:10},
        "#songView" : { borderType:"shadow", cornerRadius:5, shadowWidth:10}        
    });
    RUZEE.Borders.render();
    */

}

function playSongByHREF()
{
  PlaySong(this.getAttribute("href"));
  return false;
}

function playArtistByHREF()
{
  PlayArtist(this.getAttribute("href"));
  return false;
}

function playAlbumByHREF()
{
  PlayAlbum(this.getAttribute("href"));
  return false;
}

function attachLinks()
{
  var elements = cssQuery(".songLink");
  for(var i=0;  i < elements.length; i++)
  {
    elements[i].onclick=playSongByHREF;
  }
  elements = cssQuery(".albumLink");
  for(var i=0;  i < elements.length; i++)
  {
    elements[i].onclick=playAlbumByHREF;
  }
  elements = cssQuery(".artistLink");
  for(var i=0;  i < elements.length; i++)
  {
    elements[i].onclick=playArtistByHREF;
  }
}
function RequestArtist(artistId) {
	var artistRequestURL = baseURL + "/mp3/songs";
    dojo.io.bind(
    {
       url: artistRequestURL,
       load: loadSongs,
       error: handleAjaxFailure,
       content: 
       {
         artist: artistId
       },
       mimetype: "text/json"
    }
    );
}

function RequestAlbum(albumId) {
    var albumRequestURL = baseURL + "/mp3/songs";
    dojo.io.bind(
    {
       url: albumRequestURL,
       load: loadSongs,
       error: handleAjaxFailure,
       content: 
       {
         album: albumId
       },
       mimetype: "text/json"
    }
    );
}

function LoadTestData()
{
  var artistArray = 
    [{
       id:1,
       artistname: "artistName",
       albums:  
       [ 
         { 
           id:1,
           albumname: "albumName",
           artistname: "artistName",
           trackcount: 20,
           year: 1999,
           songs:
           [ 
             {
                   id: 1,
                   number: 1,
                   name: "song name",
                   artist:  "artistName",
                   album: "albumname",
                   length: "5:50",
                   genre: "rock"
             }
           ]
        }
      ]
    }];
    var artist = artistArray[0];
    var songCount = 0;
    for(var i= 1; i < 20; i++)
    {
      var newArtist = dojo.lang.mixin({},artist);
      newArtist.id=i;
      newArtist.artistname="artist"+i;
      newArtist.albums[0].id=i;
      newArtist.albums[0].artistname = newArtist.artistname;
      var max = 0;
      if(i==49) max = 10;
      for (j = 0; j <= max; j++) {
        var song = newArtist.albums[0].songs[0];
        newSong = dojo.lang.mixin({},song);
        newSong.id=songCount++;
        newSong.number = j;
        newSong.artistname = newArtist.artistname;
        newArtist.albums[0].songs.push(newSong);
      }
      artistArray.push(newArtist);
    }
    loadSongs("",artistArray,"");
}

// CSS Browser Selector   v0.2.3
// Documentation:         http://rafael.adm.br/css_browser_selector
// License:               http://creativecommons.org/licenses/by/2.5/
// Author:                Rafael Lima (http://rafael.adm.br)
// Contributors:          http://rafael.adm.br/css_browser_selector#contributors
var css_browser_selector = function() {
	var 
		ua = navigator.userAgent.toLowerCase(),
		is = function(t){ return ua.indexOf(t) != -1; },
		h = document.getElementsByTagName('html')[0],
		b = (!(/opera|webtv/i.test(ua)) && /msie (\d)/.test(ua)) ? ((is('mac') ? 'ieMac ' : '') + 'ie ie' + RegExp.$1)
			: is('gecko/') ? 'gecko' : is('opera') ? 'opera' : is('konqueror') ? 'konqueror' : is('applewebkit/') ? 'webkit safari' : is('mozilla/') ? 'gecko' : '',
		os = (is('x11') || is('linux')) ? ' linux' : is('mac') ? ' mac' : is('win') ? ' win' : '';
	var c = b+os+' js';
	h.className += h.className?' '+c:c;
}();

