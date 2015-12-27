/**
 * Created by aviadh on 12/18/15.
 */

$(document).ready(function () {

    $('#chunk').on('click', function () {
        $('#image').empty();
        $('#requests').empty();


        // This is cheating i know :(
        // Couldn't find JQ ajax loading with onprogress
        var img = $("<img />").attr('src', 'img/bigImg.jpg')
            .on('load', function () {
                if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) {
                    alert('broken image!');
                } else {
                    $("#image").append(img);
                }
            });

        $.ajax({
            type: "GET",
            xhrFields: {
                onprogress: function (e) {
                    console.log(e);
                    $('#requests').append('<p style="color:white;">' + e.loaded + '</p>');
                    $('#loaded').html('<p>Loaded <i>' + e.loaded/1000000+'</i> MB</p>');
                }
            },
            beforeSend: function (request) {
                request.setRequestHeader('chunked', 'yes');
            },
            url: "img/bigImg.jpg",
            success: function (msg) {
                $('#requests').append('<p style="color:white;"> Done! </p>');
            }
        });

    });
});