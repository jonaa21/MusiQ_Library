getAlbumDetail();

function getAlbumDetail() {
    let urlParams = window.location.search.substring(1).split("=");
    let item = window.sessionStorage.getItem(urlParams[1]);
    const data = JSON.parse(item);
    setAlbumDetail(data)
}


function setAlbumDetail(album) {
    let albumDetail = document.getElementById('albumDetail');
    let detail = `
        <button type="button" class="btn btn-outline-dark btn-dark" style="margin: 10px">
                <a href="#" onclick="history.back()"><i class="fas fa-arrow-left"> </i></a>
            </button>
            <h4>Title: ${album.albumTitle}</h4>
            <h5>Release Year: ${album.releaseYear}</h5>
            <h5>Artist: ${album.artist.artistName}</h5>
            
        `;
    albumDetail.innerHTML += detail;
    if (album.songList) {
        let song = `
        <ul id="songs"><b>Songs: </b></ul>
        `;
        getSongList(album);
    }
}

function getSongList(album) {
    const songs = document.getElementById('songs');
    album.songList.forEach(song => {
        console.log(song);
        const html = `
        <li>${song}</li>
        `;
        songs.innerHTML += html;
    });
}
