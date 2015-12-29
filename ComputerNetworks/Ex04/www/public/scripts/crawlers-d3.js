
var dataset = (function(){
    var res =[];
    for(var i=0; i<20; i++){
        res[i] = Math.floor(Math.random() * 10) + 1;
    }
    return res;
})();

var w = 500;
var h = 100;
var barPadding = 1;
var svg = d3
    .select("#d3")
    .append("svg")
    .attr("width",w)
    .attr("height",h);

svg.selectAll('rect')
    .data(dataset)
    .enter()
    .append('rect')
    .attr('x',function(d,i){
        return i *(w/dataset.length);
    })
    .attr('y',function(d) {
        return h - d;
    })
    .attr('width',w / dataset.length - barPadding)
    .attr('height',function(d){
        return d * 4;
    });