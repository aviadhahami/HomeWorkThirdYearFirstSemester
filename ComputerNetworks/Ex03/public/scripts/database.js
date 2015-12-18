/**
 * Created by aviadh on 12/18/15.
 */

$(document).ready(function () {

    var dataStub = [{
        name: 'a',
        age: '33'
    }, {
        name: 'b',
        age: '33'
    }, {
        name: 'c',
        age: '33'
    }, {
        name: 'd',
        age: '33'
    }];


    var result = $('#result');
    var appendRecordList = function (data) {
        clearResult();
        data.forEach(function (obj) {
            result.append('<p>' + obj.name + ' is ' + obj.age + ' years old</p>');
        })
    };
    var appendError = function (errCode) {
        clearResult();
        result.append('<p style="color:#BF360C;">Error! server responed with ' + errCode + ' </p>');
    };
    var clearResult = function () {
        result.html("");
    }


    $('#showAll').click(function () {
        $.get("/api/getDB", function (data) {
            alert("Load was performed.");
        }).done(function (res) {
            appendRecordList(res.data);
        }).fail(function (err) {
            appendError(err.status);
        });
    });

    // Bind input box
    $('#search-box').on("keyup", function () {
        var val = $('#search-box').val();
        $.get("/api/getDB?name=" + val, function (data) {
            console.log('data', data);
            alert("Load was performed.");
        }).done(function (data) {
            console.log(JSON.parse(data));
        }).fail(function (err) {
            result.html("");
            result.append('<p style="color:#BF360C;">Error! server responed with ' + err.status + ' </p>');
        });
    });

});