getArtistDetail();

function getArtistDetail() {
    let urlParams = window.location.search.substring(1).split("=");
    let item = window.sessionStorage.getItem(urlParams[1]);
    const data = JSON.parse(item);
    setArtistDetail(data)
}

function setArtistDetail(artist) {
    let artistDetail = document.getElementById('artistDetail');
    let detail = `
        <button type="button" class="btn btn-outline-dark btn-dark" style="margin: 10px">
                <a href="#" onclick="history.back()"><i class="fas fa-arrow-left"> </i></a>
            </button>
            <h4>Artist: ${artist.artistName}</h4>
            <h5>Artist Type: ${artist.artistType}</h5>
            <h5>Album: ${artist.album}</h5>
        `;
    artistDetail.innerHTML += detail;
    if (artist.artistInfo) {
        let info = `
        <h5>Artist Info: </h5><textarea rows="9" cols="70" id="artistInfo" disabled style="background-color: white; height: 200px;
                margin: 5px"> ${artist.artistInfo.info}</textarea>
        <div class="col-3" id="buttonGroup">
            <button class="btn btn-primary" id="editBtn" onclick="editInfo()">Edit Info</button>
        </div>
        `;
        artistDetail.innerHTML += info;
    }
}

function editInfo() {
    let artistInfo = document.getElementById('artistInfo');
    let editButton = document.getElementById('editBtn');
    artistInfo.removeAttribute('disabled');
    editButton.style.display = 'none';

    let buttonGroup = document.getElementById('buttonGroup');
    let submit = `
    <button class="btn btn-success" id="submit" onclick="saveInfo()">Submit</button>
    `;

    buttonGroup.innerHTML += submit;
}

function saveInfo() {
    let urlParams = window.location.search.substring(1).split("=");
    let artist = JSON.parse(window.sessionStorage.getItem(urlParams[1]));
    let info = document.getElementById("artistInfo").value;
    apiCall('PUT', `artist/update/${artist.id}`, info);
}

function apiCall(method, entity, body) {
    let url = "http://localhost:8080/musiQ_library/api/";
    let newUrl = url + entity.toLowerCase();
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            responseText = this.responseText;
        }
    };

    xhttp.onload = function () {
        if (this.status === 200) {
            alert('Success')
        } else if (this.status === 400) {
            alert('Fail');
        }
    };

    if (method === 'PUT') {
        xhttp.open(method, newUrl, true);
        xhttp.setRequestHeader('Content-Type', 'application/json');
        xhttp.send(JSON.stringify(body));
    }
}
