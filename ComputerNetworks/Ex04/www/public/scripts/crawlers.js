$(document).ready(function(){

    var toggleSections = function(){
        $('#crawler-form').toggle();
        $('#crawler-data').toggle();
        displayD3graph();

    };

    $('#toggle').on('click',function(){
       toggleSections();
    });

});