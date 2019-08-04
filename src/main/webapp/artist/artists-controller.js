loadAllArtists();
var responseText = [];

function loadAllArtists() {
    apiCall('GET', 'artist/list', null);
    if (JSON.parse(responseText)) {
        let artistDataList = JSON.parse(responseText);
        const artistList = document.getElementById('artistTable');
        artistDataList.forEach(artist => {
            const data = `
            <tr class="table-light" style="background-color: rgba(225, 225, 225, 0.8)">
                    <td id="artist">
                        <a href="./artist/artist-detail.html?id=${artist.id}" style="color:black"">${artist.artistName}</a>
                     </td>
                    <td>
                        <button type="button" class="btn btn-primary btn-sm" onclick="viewArtist(${artist.id})">
                            <i class="fas fa-eye"></i>
                        </button>
                        <button type="button" class="btn btn-danger btn-sm" onclick="deleteArtist(${artist.id})">
                            <i class="fas fa-times-circle"></i>
                        </button>
                    </td>
                    </tr>
            `;
            window.sessionStorage.setItem(`${artist.id}`, JSON.stringify(artist));
            artistList.innerHTML += data;
        });
    } else {
        alert('Something went wrong.\nPlease contact the administrator');
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

    xhttp.onload = function () {
        if (this.status === 200 && method === "DELETE") {
            alert('Success')
        } else if (this.status === 400) {
            alert('Fail');
        }
    };

    if (method === 'GET') {
        xhttp.open(method, newUrl, false);
        xhttp.send();
    } else if (method === 'DELETE') {
        xhttp.open(method, newUrl, true);
        xhttp.setRequestHeader('Content-Type', 'application/json');
        xhttp.send();
    }
}

function viewArtist(id) {
    window.location = `artist-detail.html?id=${id}`;
}

function deleteArtist(id) {
    apiCall("DELETE", `artist/${id}`)
}
