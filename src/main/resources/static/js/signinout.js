function onSignIn() {
    gapi.load('auth2', function() {
        auth2 = gapi.auth2.getAuthInstance();
        auth2.signIn();

        $('#loggedin').val('true');
        $('#loginform').submit();
    });
};

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut();

    $('#loggedin').val('false');
    $('#loginform').submit();
};
