window.onload=function() {

    var urlParam = function(url, key) {
        var result = new RegExp(key + "=([^&]*)", "i").exec(url);
        return result && result[1] || ""; 
    }


    var formatData =function(data){
        var rows = [];
        $.each(data.values, function (index, item) {
            var validwps = (typeof item.wpsStatusUrl != "undefined"),
            executionId = validwps ? urlParam(item.wpsStatusUrl,"executionId") : "",
            wpsStatusUrlAnchor = validwps ? '<a href="' + item.wpsStatusUrl + '">' + item.wpsStatusUrl +'</a>' : "",
            pairs = item.requestOptions.split(';'),
            optionItems = {};
            $.each(pairs, function (index, pair) {
                if (pair.indexOf("=") != -1) {
                    pairEntry = pair.split("=");
                    optionItems[pairEntry[0]] = pairEntry[1];
                }
            });
            var bboxvalues = "";
            if (typeof(optionItems.bbox) != "undefined") {
                bboxvalues = optionItems.bbox.split(',');
                if (bboxvalues.length !== 4) {
                    bboxvalues.push(optionItems.bbox);
                }

            }


            rows.push({ cell:
                [   item.id,
                    item.name,
                    item.status,
                    item.requestTimestamp,
                    item.memberUserId,
                    item.memberAccountId,
                    item.lastModifiedBy,
                    item.lastModifiedTime,
                    wpsStatusUrlAnchor,
                    executionId,
                    item.requestOptions,
                    (typeof(optionItems.layers) != "undefined") ? optionItems.layers : "",
                    (typeof(optionItems.name) != "undefined") ? optionItems.name : "",
                    (typeof(optionItems.format) != "undefined") ? optionItems.format : "",
                    (typeof(optionItems.resolution) != "undefined") ? optionItems.resolution : "",
                    (typeof(optionItems.projection) != "undefined") ? optionItems.projection : "",
                    (typeof(optionItems.interpolation) != "undefined") ? optionItems.interpolation : "",
                    (typeof(bboxvalues[0]) != "undefined") ? bboxvalues[0] : "",
                    (typeof(bboxvalues[1]) != "undefined") ? bboxvalues[1] : "",
                    (typeof(bboxvalues[2]) != "undefined") ? bboxvalues[2] : "",
                    (typeof(bboxvalues[3]) != "undefined") ? bboxvalues[3] : "",
                    (typeof(optionItems.width) != "undefined") ? optionItems.width : "",
                    (typeof(optionItems.height) != "undefined") ? optionItems.height : "",
                    (typeof(optionItems.tileSize) != "undefined") ? optionItems.tileSize : "",
                ]
            });
        });

        return {
            total: data.tableData.count,
            page: data.tableData.page,
            rows: rows
        };
    }

    $("#flex1").flexigrid({
        url: 'library_requests',
        dataType: 'json',
        method: 'GET',
        preProcess: formatData,
        colModel: [
            { display: 'id', name: 'id', width: 50, sortable: true, align: 'left' },
            { display: 'Name', name: 'name', width: 170, sortable: true, align: 'left' },
            { display: 'Status', name: 'status', width: 50, sortable: true, align: 'left' },
            { display: 'Request Time', name: 'request_timestamp', width: 165, sortable: true, align: 'left' },
            { display: 'User', name: 'member_user_id', width: 40, sortable: true, align: 'left' },
            { display: 'Account', name: 'member_account_id', width: 60, sortable: true, align: 'left' },
            { display: 'Last Modified By', name: 'last_modified_by', width: 150, sortable: true, align: 'left' },
            { display: 'Last Modified Time', name: 'last_modified_time', width: 165, sortable: true, align: 'left' },
            { display: 'WPS Status Url', name: 'wps_status_url', width: 240, sortable: true, align: 'left' },
            { display: 'Execution Id', name: 'derived_execution_id', width: 240, sortable: false, align: 'left' },
            { display: 'Request Options', name: 'request_options', width: 140, sortable: true, align: 'left' },
            { display: 'layers', name: 'option_layers', width: 170, sortable: false, align: 'left' },
            { display: 'name', name: 'option_name', width: 170, sortable: false, align: 'left' },
            { display: 'format', name: 'option_format', width: 40, sortable: false, align: 'right' },
            { display: 'resolution', name: 'option_resolution', width: 60, sortable: false, align: 'right' },
            { display: 'projection', name: 'option_projection', width: 60, sortable: false, align: 'right' },
            { display: 'interpolation', name: 'option_interpolation', width: 70, sortable: false, align: 'right' },
            { display: 'bbox[0]', name: 'option_bbox0', width: 80, sortable: false, align: 'left', nowrap: false },
            { display: 'bbox[1]', name: 'option_bbox1', width: 80, sortable: false, align: 'left', nowrap: false },
            { display: 'bbox[2]', name: 'option_bbox2', width: 80, sortable: false, align: 'left', nowrap: false },
            { display: 'bbox[3]', name: 'option_bbox3', width: 80, sortable: false, align: 'left', nowrap: false },
            { display: 'width', name: 'option_width', width: 50, sortable: false, align: 'right' },
            { display: 'height', name: 'option_height', width: 50, sortable: false, align: 'right' },
            { display: 'tile size', name: 'option_tilesize', width: 50, sortable: false, align: 'right' },
        ],
        title: 'Library Requests',
        usepager: true,
        useRp: true,
        rp: 50,
        showTableToggleBtn: false,
        width: 700,
        height: 600
    });

}