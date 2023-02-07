var myCenter=new google.maps.LatLng(40.7065983,-74.0107104);
    function initialize()
    {
        var mapProp = {
            center:myCenter,
            scrollwheel: false,
            zoom:11,
            mapTypeId:google.maps.MapTypeId.ROADMAP
        };
        var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
        var marker=new google.maps.Marker({
            position:myCenter,
            map: map,
        });
	
		var styles =  [
			{
				"stylers": [
					{
						"hue": "#C70909"
					},
					{
						"saturation": 10
					}
				]
			},
			{
				"featureType": "water",
				"stylers": [
					{
						"color": "#effefd"
					}
				]
			},
			{
				"featureType": "all",
				"elementType": "labels",
				"stylers": [
					{
						"visibility": "off"
					}
				]
			},
			{
				"featureType": "administrative",
				"elementType": "labels",
				"stylers": [
					{
						"visibility": "on"
					}
				]
			},
			{
				"featureType": "road",
				"elementType": "all",
				"stylers": [
					{
						"visibility": "off"
					}
				]
			},
			{
				"featureType": "transit",
				"elementType": "all",
				"stylers": [
					{
						"visibility": "off"
					}
				]
			}
		];

		
		

		
		
        map.setOptions({styles: styles});
        marker.setMap(map);
    }
google.maps.event.addDomListener(window, 'load', initialize);

	