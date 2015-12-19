/**
 * Created by aviadh on 12/18/15.
 */

$(document).ready(function () {

    var result = $('#result');
    var resultCount = $('#resultCount');

    var appendRecordList = function (data) {
        clearResult();
        resultCount.text(data.length);
        var i = 1;
        data.forEach(function (obj) {

            result.append(
                '<tr><td>' + i + '</td>'
                + '<td>' + obj.name + '</td>'
                + '<td>' + obj.age + '</td></tr>');
            i++;
        });
    };
    var appendError = function (errCode) {
        clearResult();
        resultCount.text(0);
        result.append('<tr style="color:#BF360C;">' +
            '<td colspan="3">Error! server responed with ' + errCode + '</td></tr>');
    };
    var clearResult = function () {
        $("#result tbody").empty();
    };


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