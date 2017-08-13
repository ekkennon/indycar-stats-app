function addSeason() {
    if (ajax) {
        ajax.open('get','stats/addSeason?year=' + $("#year").val());
        ajax.send(null);
    } else {
        document.selection.submit();
    }
}

function addDriver() {
    if (ajax) {
        ajax.open('get','stats/addDriver?driver=' + $("#driverid").val());
        ajax.send(null);
    } else {
        document.selection.submit();
    }
}

function addRace() {
    if (ajax) {
        ajax.open('get','stats/addRace?race=' + $("#raceid").val());
        ajax.send(null);
    } else {
        document.selection.submit();
    }
}

function addSession() {
    if (ajax) {
        ajax.open('get','stats/addSession?id=' + $("#sessionid").val());
        ajax.send(null);
    } else {
        document.selection.submit();
    }
}

function viewSeason() {
    if (ajax) {
        ajax.open('get', 'stats/viewBySeason?id=' + $("#year").val());
        ajax.send(null);
    } else {
        document.selection.submit();
    }
}
