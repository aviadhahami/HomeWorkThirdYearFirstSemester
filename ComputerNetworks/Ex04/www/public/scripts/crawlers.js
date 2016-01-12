$(document).ready(function(){

    var toggleSections = function(){
        $('#crawler-form').toggle();
        $('#crawler-data').toggle();
        displayD3graph();

    };

    $('#toggle').on('click',function(){
        toggleSections();
    });

    var crawlerDataProto = {
        respectRobots : true,
        images : {
            types:{
                bmp: '',
                jpg:'',
                png:'',
                gif:'',
                ico:''
            },
            count :0,
            totalByteCount : 0
        },
        videos : {
            types : {
                avi:'',
                mpg:'',
                mp4:'',
                wmv:'',
                mov:'',
                flv:'',
                swf:'',
                mkv:''
            },
            count:0,
            totalByteCount : 0
        },
        documents : {
            types :{
                pdf:'',
                doc:'',
                docx:'',
                xls:'',
                xlsx:'',
                ppt:'',
                pptx:''
            },
            count : 0,
            totalByteCount : 0
        },
        links :{
            internal :{
                count: ''
            },
            external : {
                count :''
            }
        }
    };
// Number of internal links (pointing into the domain)
//     Number of external links (pointing outside the domain)
//     Number of domains the crawled domain is connected to
// The domains the crawled domain is connected to
//    o If the linked domain has been crawled, the text should be a link to this domain’s page.
// If requested, the opened ports.
// Average RTT in milliseconds (Time passed from sending the HTTP request until received the HTTP response, excluding reading the response).
});