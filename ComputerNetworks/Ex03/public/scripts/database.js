/**
 * Created by aviadh on 12/18/15.
 */

$(document).ready(function () {
    var result = $('#result');
    $('#showAll').click(function () {
        $.get("/getDB", function (data) {
            console.log('data', data);
            alert("Load was performed.");
        }).done(function (data) {
            console.log(data);
        }).fail(function (err) {
            result.html("");
            result.append('<p style="color:#BF360C;">Error! server responed with ' + err.status + ' </p>');
        });
    });

    // Bind input box
    $('#search-box').on("keyup", function () {
        var val = $('#search-box').val();
        $.get("/getDB", function (data) {
            console.log('data', data);
            alert("Load was performed.");
        }).done(function (data) {
            console.log(data);
        }).fail(function (err) {
            result.html("");
            result.append('<p style="color:#BF360C;">Error! server responed with ' + err.status + ' </p>');
        });
    });

});
/**
 * Created by aviadh on 12/18/15.
 */

$(document).ready(function () {
    var result = $('#result');
    $('#showAll').click(function () {
        $.get("/getDB", function (data) {
            console.log('data', data);
            alert("Load was performed.");
        }).done(function (data) {
            console.log(data);
        }).fail(function (err) {
            result.html = "";
        });
    });

    // Bind input box
    $('#search-box').on("keyup", function () {
        var val = $('#search-box').val();
        $.get("/getDB", function (data) {
            console.log(data);
            $(".result").html(data);
            alert("Load was performed.");
        });
    });

});