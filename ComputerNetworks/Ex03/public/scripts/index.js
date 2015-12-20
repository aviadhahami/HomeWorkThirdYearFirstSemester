/**
 * Created by aviadh on 12/20/15.
 */


$(document).ready(function () {
    var displayPage = function () {
        $('.disclaimer').css('display', 'none');
        $('.real-content').css('display', 'block');
    };

    // Decide what to display
    if (sessionStorage.hasOwnProperty('firstRun') && sessionStorage.firstRun) {
        displayPage()
    } else {
        sessionStorage.firstRun = true;
    }


    $('#bad').on('click', function () {
        alert(':(');
        displayPage();
    });

    $('#good').on('click', function () {
        alert('Thanks!');
        displayPage();
    });
    $('#ok').on('click', function () {
        displayPage();
    })

});