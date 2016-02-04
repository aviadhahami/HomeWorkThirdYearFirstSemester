// vanilla

// JQ
$(document).ready(function(){
    var portScanInput = $('#portScan');
    var robotsInput =$('#robots');
    var domain = $('#domain');
    portScanInput.prop('disabled',true);
    robotsInput.prop('disabled',true);

    domain.on('keyup',function(e){
        if(e.currentTarget.value.length >0){
            portScanInput.prop('disabled',false);
            robotsInput.prop('disabled',false);
        }else{
            portScanInput.prop('disabled',true);
            robotsInput.prop('disabled',true);
        }
    });

});