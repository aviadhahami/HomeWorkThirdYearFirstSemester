/**
 * Created by aviadh on 12/18/15.
 */

$(document).ready(function () {
    $('#chunk').on('click', function () {
        alert('chunk');
        $.ajax({
            type:"GET",
            beforeSend: function (request)
            {
                request.setRequestHeader('chunked', 'yes');
            },
            url: "../",
            success: function(msg) {
                console.log(msg);
            }
        });
    });
});