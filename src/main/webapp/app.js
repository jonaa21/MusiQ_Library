getAllData();

function getAllData() {
    loadAlbums();
    loadArtist();
    loadSongs();
}

function loadSongs() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let songDataList = JSON.parse(this.responseText);
            let song = ' <tr class="col-6"> ';

            // for (let index = 0; index < songDataList.length; index++) {
                for (let index = 0; index < 5; index++) {
                song +=
                    '<td>'+
                    '<button class=""></button>'+
                    '<p class="h5"><span>Title: ' + songDataList[index].title + '</span></p>' +
                    '<p class="h6"><span>Artist: ' + songDataList[index].artist + '</span></p>' +
                    '<p class="h6"><span>Album: ' + songDataList[index].album + '</span></p>'+
                    '</td>'
                ;
            }
            song += '</tr>';
            document.getElementById("songData").innerHTML = song;
        }
    };
    xhttp.open("GET", "api/song/list", true);
    xhttp.send();
}

function loadArtist() {
    let artists = new XMLHttpRequest();
    if (this.readyState === 4 && this.status === 200){
        let artistList = JSON.parse(this.responseText);
    }
    artists.open("GET", "api/artist/list", true);
    artists.send();
}

function loadAlbums() {
    let albums = new XMLHttpRequest();
    if (this.readyState === 4 && this.status === 200){
        let albumList = JSON.parse(this.responseText);
    }
    albums.open("GET", "api/album/list", true);
    albums.send();
}
