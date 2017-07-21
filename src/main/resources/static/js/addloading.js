function addSeason()
        {
            var dropdown = document.getElementById("year");
            var year = dropdown.options[dropdown.selectedIndex].value;
            if (ajax) {
                ajax.open('get','stats/addSeason?year=' + year);
                ajax.send(null);
            } else {
                document.selection.submit();
            }
        }
        function addDriver()
        {
            var dropdown = document.getElementById("driverid");
            var id = dropdown.options[dropdown.selectedIndex].value;
            if (ajax) {
                ajax.open('get','stats/addDriver?driver=' + id);
                ajax.send(null);
            } else {
                document.selection.submit();
            }
        }
        function addRace()
        {
            var dropdown = document.getElementById("raceid");
            var id = dropdown.options[dropdown.selectedIndex].value;
            if (ajax) {
                ajax.open('get','stats/addRace?race=' + id);
                ajax.send(null);
            } else {
                document.selection.submit();
            }
        }
        function addSession()
        {
            var dropdown = document.getElementById("sessionid");
            var id = dropdown.options[dropdown.selectedIndex].value;
            if (ajax) {
                ajax.open('get','stats/addSession?id=' + id);
                ajax.send(null);
            } else {
                document.selection.submit();
            }
        }