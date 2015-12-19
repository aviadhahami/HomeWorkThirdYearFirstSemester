/**
 * Created by aviadh on 12/18/15.
 */

$(document).ready(function () {

    var result = $('#result');
    var resultCount = $('#resultCount');

    var appendRecordList = function (data) {
        clearResult();
        resultCount.text(data.length);
        data.forEach(function (obj) {

            result.append('<li>' + obj.name + ' is ' + obj.age + ' years old</li>');
        })
    };
    var appendError = function (errCode) {
        clearResult();
        resultCount.text(0);
        result.append('<p style="color:#BF360C;">Error! server responed with ' + errCode + ' </p>');
    };
    var clearResult = function () {
        result.html("");
    }


    $('#showAll').click(function () {
        $.get("/api/getDB", function (data) {

        }).done(function (res) {
           // console.log(JSON.parse(res));
            appendRecordList(JSON.parse(res));
        }).fail(function (err) {
            appendError(err.status);
        });
    });

    // Bind input box
    $('#search-box').on("keyup", function () {
        var val = $('#search-box').val();
        $.get("/api/getDB?name=" + val, function (data) {

        }).done(function (res) {
            appendRecordList(JSON.parse(res));
        }).fail(function (err) {
            appendError(err.status);
        });
    });

});