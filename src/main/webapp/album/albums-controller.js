loadAlbums();

function loadAlbums() {
    apiCall('GET', 'album/list', null);
    if (JSON.parse(responseText)) {
        let albumDataList = JSON.parse(responseText);
        const albumList = document.getElementById('albumData');
        albumDataList.forEach(album => {
            const data = `<tr class="table-light" style="background-color: rgba(225, 225, 225, 0.8)">
            <a href="./album/album-detail.html?id=${album.id}" id="selectedAlbum" style="color:black">
                    <p><b>Album: ${album.albumTitle}</b><br>
                    Released: ${album.releaseYear}<br>
                    Artist: ${album.artist.artistName} <br>
                    Artist Type:  ${album.artist.artistType.artistType} <br></p>
                    </a></tr>
            `;
            window.sessionStorage.setItem(`${album.id}`, JSON.stringify(album));
            albumList.innerHTML += data;
        })
    }
}

function apiCall(method, entity) {
    let url = "http://localhost:8080/musiQ_library/api/";
    let newUrl = url + entity.toLowerCase();
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            responseText = this.responseText;
        }
    };

    if (method === 'GET') {
        xhttp.open(method, newUrl, false);
        xhttp.send();
    }
}
