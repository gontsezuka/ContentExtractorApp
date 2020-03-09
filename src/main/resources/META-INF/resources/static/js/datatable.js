/**
 * 
 */
$(document).ready( function () {
	 var table = $('#filesTabless').DataTable({
			"sAjaxSource": "/getfiles",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		      { "mData": "filename" },
				  { "mData": "createddate" }
			]
	 })
});

$(document).ready( function () {
	 var table = $('#documentsTables').DataTable({
			"sAjaxSource": "/getdocuments",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		      { "mData": "documentname" },
				  { "mData": "createddate" }
			]
	 })
});

$('#filesTable').DataTable({
    "processing": true,
    "serverSide": true,
    "ajax": {
        "url": "/getfiles",
        "type": "POST",
        "dataType": "json",
        "contentType": "application/json",
        "data": function (d) {
            return JSON.stringify(d);
        }
    },
    "columns": [
        {"data": "id", "width": "20%"},
        {"data": "alfrescoid","width": "20%"},
        {"data": "createddate", "width": "20%"},
        {"data": "filename", "width": "20%"},
        {"data": "user_id", "width": "20%"}
    ]
});