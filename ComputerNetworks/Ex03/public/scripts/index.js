/**
 * Created by aviadh on 12/20/15.
 */


$(document).ready(function () {


    var displayDisclaimer = function () {
        $('.disclaimer').css('display', 'block');
        $('.real-content').css('display', 'none');
    };
    var displayPage = function(){
        $('.disclaimer').css('display', 'none');
        $('.real-content').css('display', 'block');
    };

    // Decide what to display
    if (!sessionStorage.hasOwnProperty('firstRun') || !sessionStorage.firstRun) {
        displayDisclaimer();
    }

    $('#bad').on('click', function () {
        alert(':(');
        sessionStorage.firstRun = true;
        displayPage();

    });

    $('#good').on('click', function () {
        alert('Thanks!');
        sessionStorage.firstRun = true;
        displayPage();
    });
    $('#ok').on('click', function () {
        displayPage();
        sessionStorage.firstRun = true;
        displayPage();
    })

});