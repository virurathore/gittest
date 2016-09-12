

	function ConvertFormToShopJSON(form) {
		var array = jQuery(form).serializeArray();
		var json = {};
		var address = {};
		jQuery.each(array, function() {
			if (this.name == "shopName") {
				json[this.name] = this.value || '';
			} else {
				address[this.name] = this.value || '';
			}
		});
		//alert(JSON.stringify(address));
		json["shopAddress"] = address;
		//alert(JSON.stringify(json));
		return JSON.stringify(json);
	}
	function addRow(jsondata,tbody){
		console.log(tbody);
		var row= '<tr>';
		 console.log(" row data : "+JSON.stringify(jsondata));
		 row += '<td>' + jsondata['shopsName'] +'</td>';
		 row += '<td>' + jsondata['shopAddress']['number'] +'</td>';
		 row += '<td>' + jsondata['shopAddress']['postCode'] +'</td>';
		 row += '<td>' + jsondata['shopLongitude'] +'</td>';
		 row += '<td>' + jsondata['shopLatitude'] +'</td>';
			row += '</tr>';
			tbody.append(row);
	}
	function listPageShow( event ){
		console.log("listing data");
		var searchurl = "/api/shops?all=1"
		$.ajax({
			type : "GET",
			url : searchurl
		}).done(function(jsondata) {
			console.log("get all result "+jsondata);
			console.log("not result "+ !jsondata);
			console.log("result length"+ jsondata.length);
			if(jsondata && jsondata.length>0){
				$('#listTable > tbody').empty();
				var tbody = jQuery('#listTable > tbody');
				console.log("success");
				 for(var i=0; i<jsondata.length; ++i ){
					 addRow(jsondata[i],tbody);
				 } 
			}else{
				alert("No shop in system");
			}

		})
		.fail(function() {
		alert("unable to retrive data from server, try again");
		});

	}
	$(function(){
		$( "[data-role='header'], [data-role='footer']" ).toolbar();
		 $('#myTable > tbody').empty();
	});
	jQuery(document).on('ready', function() {
//		$( "#nav_add" ).bind( "click", addPageShow );
//		$( "#nav_search" ).bind( "click", searchPageShow );
		$( "#nav_list" ).bind( "click", listPageShow );
	 

		jQuery('form#search-shop').bind('submit', function(event) {
			event.preventDefault();
			var form = this;
			var qs = $(form).serialize();
			var searchurl = "/api/shops?" + qs;
			//alert("hitting : " + searchurl);
			$.ajax({
				type : "GET",
				url : searchurl
			}).done(function(jsondata) {
				if(jsondata && jsondata.length>0){
					$('#searchTable > tbody').empty();
					var tbody = jQuery('#searchTable > tbody');
					console.log("success");
					 for(var i=0; i<jsondata.length; ++i ){
					 //alert(jsondata[i])
						 addRow(jsondata[i],tbody);
					 } 
				}else{
					alert("No shop found near by");
				}
			}).fail(function() {
				alert("unable to retrive data from server, try again");
				console.log("failed");
			});

		});
		jQuery('form#add-new-shop').bind('submit', function(event) {
			event.preventDefault();

			var form = this;
			var json = ConvertFormToShopJSON(form);
			console.log("json :" + json);
			$.ajax({
				type : "POST",
				url : "/api/shops",
				data : json,
				contentType : "application/json; charset=utf-8",
				dataType : "json"
			}).done(function(jsondata) {
				console.log("success");
				//alert(jsondata);
				if(jsondata){
				$('#addTable > tbody').empty();
					var tbody = jQuery('#addTable > tbody');
				 addRow(jsondata,tbody);
				 }
			}).fail(function() {
				alert("unable to save data to server, try again");
			});
			return true;
		});
	});

