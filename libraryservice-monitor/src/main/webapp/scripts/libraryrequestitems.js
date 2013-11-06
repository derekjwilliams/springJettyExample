window.onload=function() {

    $("#flex1").flexigrid({
        url: 'library_request_items',
        dataType: 'json',
        method: 'GET',
        preProcess: formatData,

        colModel: [
            { display: 'id', name: 'id', width: 30, sortable: true, align: 'left' },
            { display: 'libraryRequestId', name: 'library_request_id', width: 50, sortable: true, align: 'left' },
            { display: 'retrievePath', name: 'retrieve_path', width: 740, sortable: true, align: 'left' },
            { display: 'sizeInBytes', name: 'size_in_bytes', width: 80, sortable: true, align: 'left' },
            { display: 'lastModifiedBy', name: 'last_modified_by', width: 80, sortable: true, align: 'left' },
            { display: 'lastModifiedTime', name: 'last_modified_time', width: 140, sortable: true, align: 'left' }
        ],
        title: 'Library Request Items',
        usepager: true,
        useRp: true,
        rp: 50,
        showTableToggleBtn: true,
        width: 700,
        height: 600
    });

    function formatData(data){

        var rows = [];
        $.each(data.values, function (index, item) {
            rows.push({ cell:
                [   item.id,
                    item.libraryRequestId,
                    item.retrievePath,
                    item.sizeInBytes,
                    item.lastModifiedBy,
                    item.lastModifiedTime
                ]
            });
        });

        return {
            total: data.tableData.count,
            page: data.tableData.page,
            rows: rows
        };


    }
}