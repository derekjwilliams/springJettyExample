var operatorLabels = {"=":"is","!":"is not","o":"open","c":"closed","!*":"none","*":"any",">=":">=","<=":"<=","><":"between","<t+":"in less than",">t+":"in more than","><t+":"in the next","t+":"in","t":"today","w":"this week",">t-":"less than days ago","<t-":"more than days ago","><t-":"in the past","t-":"days ago","~":"contains","!~":"doesn't contain","=p":"any issues in project","=!p":"any issues not in project","!p":"no issues in project"};
var operatorByType = {"list":["=","!"],"list_status":["o","=","!","c","*"],"list_optional":["=","!","!*","*"],"list_subprojects":["*","!*","="],"date":["=",">=","<=","><","<t+",">t+","><t+","t+","t","w",">t-","<t-","><t-","t-","!*","*"],"date_past":["=",">=","<=","><",">t-","<t-","><t-","t-","t","w","!*","*"],"string":["=","~","!","!~","!*","*"],"text":["~","!~","!*","*"],"integer":["=",">=","<=","><","!*","*"],"float":["=",">=","<=","><","!*","*"],"relation":["=","=p","=!p","!p","!*","*"]};
var availableFilters = {"status_id":{"type":"list_status","name":"Status","values":[["New","1"],["In Progress","2"],["Resolved","3"],["Feedback","4"],["Closed","5"],["Rejected","6"]]},"tracker_id":{"type":"list","name":"Tracker","values":[["Bug","1"],["Feature","2"],["Support","3"]]},"priority_id":{"type":"list","name":"Priority","values":[["Low","1"],["Normal","2"],["High","3"],["Urgent","4"],["Immediate","5"]]},"subject":{"type":"text","name":"Subject"},"created_on":{"type":"date_past","name":"Created"},"updated_on":{"type":"date_past","name":"Updated"},"start_date":{"type":"date","name":"Start date"},"due_date":{"type":"date","name":"Due date"},"estimated_hours":{"type":"float","name":"Estimated time"},"done_ratio":{"type":"integer","name":"% Done"},"relates":{"type":"relation","name":"Related to"},"duplicates":{"type":"relation","name":"Duplicates"},"duplicated":{"type":"relation","name":"Duplicated by"},"blocks":{"type":"relation","name":"Blocks"},"blocked":{"type":"relation","name":"Blocked by"},"precedes":{"type":"relation","name":"Precedes"},"follows":{"type":"relation","name":"Follows"},"copied_to":{"type":"relation","name":"Copied to"},"copied_from":{"type":"relation","name":"Copied from"},"assigned_to_id":{"type":"list_optional","name":"Assignee","values":[["<< me >>","me"],["Derek Williams","4"],["Derek Williams Admin","1"]]},"author_id":{"type":"list","name":"Author","values":[["<< me >>","me"],["Derek Williams","4"],["Derek Williams Admin","1"]]},"assigned_to_role":{"type":"list_optional","name":"Assignee's role","values":[["Manager","3"],["Developer","4"],["Reporter","5"]]},"watcher_id":{"type":"list","name":"Watcher","values":[["<< me >>","me"]]},"is_private":{"type":"list","name":"Private","values":[["yes","1"],["no","0"]]}};
var labelDayPlural = "days";
var allProjects = [["bulb","2"],["geoserver","3"],["phigs","1"]];

window.onload=function() {

    initFilters();
    addFilter("status_id", "o", [""]);

    function toggleOperator(field) {
        var fieldId = field.replace('.', '_');
        var operator = $("#operators_" + fieldId);
        switch (operator.val()) {
            case "!*":
            case "*":
            case "t":
            case "w":
            case "o":
            case "c":
            enableValues(field, []);
            break;
            case "><":
            enableValues(field, [0,1]);
            break;
            case "<t+":
            case ">t+":
            case "><t+":
            case "t+":
            case ">t-":
            case "<t-":
            case "><t-":
            case "t-":
            enableValues(field, [2]);
            break;
            case "=p":
            case "=!p":
            case "!p":
            enableValues(field, [1]);
            break;
            default:
            enableValues(field, [0]);
            break;
        }
    } 
    function enableValues(field, indexes) {
        var fieldId = field.replace('.', '_');
        $('#tr_'+fieldId+' td.values .value').each(function(index) {
            if ($.inArray(index, indexes) >= 0) {
                $(this).removeAttr('disabled');
                $(this).parents('span').first().show();
            } else {
                $(this).val('');
                $(this).attr('disabled', true);
                $(this).parents('span').first().hide();
            }
            if ($(this).hasClass('group')) {
                $(this).addClass('open');
            } else {
                $(this).show();
            }
        });
    } 
    
    function buildFilterRow(field, operator, values) {
        var fieldId = field.replace('.', '_');
        var filterTable = $("#filters-table");
        var filterOptions = availableFilters[field];
        var operators = operatorByType[filterOptions['type']];
        var filterValues = filterOptions['values'];
        var i, select;
        var tr = $('<tr class="filter">').attr('id', 'tr_'+fieldId).html(
            '<td class="field"><input checked="checked" id="cb_'+fieldId+'" name="f[]" value="'+field+'" type="checkbox"><label for="cb_'+fieldId+'"> '+filterOptions['name']+'</label></td>' +
            '<td class="operator"><select id="operators_'+fieldId+'" name="op['+field+']"></td>' +
            '<td class="values"></td>'
            );
        filterTable.append(tr);
        select = tr.find('td.operator select');
        for (i=0;i<operators.length;i++){
            var option = $('<option>').val(operators[i]).text(operatorLabels[operators[i]]);
            if (operators[i] == operator) {option.attr('selected', true)};
            select.append(option);
        }
        select.change(function(){toggleOperator(field)});
        switch (filterOptions['type']){
            case "list":
            case "list_optional":
            case "list_status":
            case "list_subprojects":
            tr.find('td.values').append(
                '<span style="display:none;"><select class="value" id="values_'+fieldId+'_1" name="v['+field+'][]"></select>' +
                ' <span class="toggle-multiselect">&nbsp;</span></span>'
                );
            select = tr.find('td.values select');
            if (values.length > 1) {select.attr('multiple', true)};
            for (i=0;i<filterValues.length;i++){
                var filterValue = filterValues[i];
                var option = $('<option>');
                if ($.isArray(filterValue)) {
                    option.val(filterValue[1]).text(filterValue[0]);
                    if ($.inArray(filterValue[1], values) > -1) {option.attr('selected', true);}
                } else {
                    option.val(filterValue).text(filterValue);
                    if ($.inArray(filterValue, values) > -1) {option.attr('selected', true);}
                }
                select.append(option);
            }
            break;
            case "date":
            case "date_past":
            tr.find('td.values').append(
                '<span style="display:none;"><input type="text" name="v['+field+'][]" id="values_'+fieldId+'_1" size="10" class="value date_value" /></span>' +
                ' <span style="display:none;"><input type="text" name="v['+field+'][]" id="values_'+fieldId+'_2" size="10" class="value date_value" /></span>' +
                ' <span style="display:none;"><input type="text" name="v['+field+'][]" id="values_'+fieldId+'" size="3" class="value" /> '+labelDayPlural+'</span>'
                );
            $('#values_'+fieldId+'_1').val(values[0]).datepicker(datepickerOptions);
            $('#values_'+fieldId+'_2').val(values[1]).datepicker(datepickerOptions);
            $('#values_'+fieldId).val(values[0]);
            break;
            case "string":
            case "text":
            tr.find('td.values').append(
                '<span style="display:none;"><input type="text" name="v['+field+'][]" id="values_'+fieldId+'" size="30" class="value" /></span>'
                );
            $('#values_'+fieldId).val(values[0]);
            break;
            case "relation":
            tr.find('td.values').append(
                '<span style="display:none;"><input type="text" name="v['+field+'][]" id="values_'+fieldId+'" size="6" class="value" /></span>' +
                '<span style="display:none;"><select class="value" name="v['+field+'][]" id="values_'+fieldId+'_1"></select></span>'
                );
            $('#values_'+fieldId).val(values[0]);
            select = tr.find('td.values select');
            for (i=0;i<allProjects.length;i++){
                var filterValue = allProjects[i];
                var option = $('<option>');
                option.val(filterValue[1]).text(filterValue[0]);
                if (values[0] == filterValue[1]) {option.attr('selected', true)};
                select.append(option);
            }
            case "integer":
            case "float":
            tr.find('td.values').append(
                '<span style="display:none;"><input type="text" name="v['+field+'][]" id="values_'+fieldId+'_1" size="6" class="value" /></span>' +
                ' <span style="display:none;"><input type="text" name="v['+field+'][]" id="values_'+fieldId+'_2" size="6" class="value" /></span>'
                );
            $('#values_'+fieldId+'_1').val(values[0]);
            $('#values_'+fieldId+'_2').val(values[1]);
            break;
        } 
    }
    function toggleFilter(field) {
        var fieldId = field.replace('.', '_');
        if ($('#cb_' + fieldId).is(':checked')) {
            $("#operators_" + fieldId).show().removeAttr('disabled');
            toggleOperator(field);
        } else {
            $("#operators_" + fieldId).hide().attr('disabled', true);
            enableValues(field, []);
        } 
    }

    function addFilter(field, operator, values) {
        var fieldId = field.replace('.', '_'),
        tr = $('#tr_'+fieldId);

        if (tr.length > 0) {
            tr.show();
        } else {
            buildFilterRow(field, operator, values);
        }

        $('#cb_'+fieldId).attr('checked', true);
        
        toggleFilter(field);
        
        $('#add_filter_select').val('').children('option').each(function(){
            if ($(this).attr('value') == field) {
              $(this).attr('disabled', true);
            }
        });
    } 
    
    function initFilters(){
        $('#add_filter_select').change(function(){
            addFilter($(this).val(), '', []);
        });
        $('#filters-table td.field input[type=checkbox]').each(function(){
            toggleFilter($(this).val());
        }); 
    }

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