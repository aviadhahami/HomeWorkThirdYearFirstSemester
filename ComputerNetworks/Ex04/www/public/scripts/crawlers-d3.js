var barPadding =1;
var dataset = [5, 10, 13, 19, 21, 25, 22, 18, 15, 13,
    11, 12, 15, 20, 18, 17, 16, 18, 23, 25];




var displayD3graph =function() {
    d3.select("#d3").selectAll("*").remove();
//Width and height
    var w = 500;
    var h = 100;
    var barPadding = 1;

//Create SVG element
    var svg = d3.select("#d3")
        .append("svg")
        .attr("width", w)
        .attr("height", h);

    svg.selectAll("rect")
        .data(dataset)
        .enter()
        .append("rect")
        .attr("x", function (d, i) {
            return i * (w / dataset.length);
        })
        .attr("y", h)
        .attr("width", function (d) {
            return w / dataset.length - barPadding;
        })
        .attr("height", 0)
        .transition()
        .delay(function (d, i) {
            return 100 * i;
        })
        .duration(200)
        .attr("y", function (d) {
            return h - (d * 4);
        })
        .attr("height", function (d) {
            return d * 4;
        })
        .attr("fill", function (d) {
            return "rgb(0, 0, " + (d * 10) + ")";
        });

    svg.selectAll("text")
        .data(dataset)
        .enter()
        .append("text")
        .text(function (d) {
            return d;
        })
        .attr("text-anchor", "middle")
        .transition()
        .attr("x", function (d, i) {
            return i * (w / dataset.length) + (w / dataset.length - barPadding) / 2;
        })
        .attr("y", function (d) {
            return h - (d * 4) + 14;
        })
        .attr("font-family", "sans-serif")
        .attr("font-size", "11px")
        .attr("fill", "rgba(0,0,0,0)")
        .delay(function (d, i) {
            return 100 * i;
        })
        .transition()
        .attr("fill", "white");

};

