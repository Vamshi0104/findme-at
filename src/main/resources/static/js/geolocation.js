document.getElementById("go-button").disabled = true;
var positionOptions = {
	  enableHighAccuracy : true
	};
function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.watchPosition(showPosition, catchError, positionOptions);
  } else { 
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
}
function showPosition(position) {	
    var x=position.coords.latitude + "," + position.coords.longitude;
	document.getElementById("location").value=x;
}

function catchError(positionError) {
  switch(positionError.code)
  {
	case positionError.TIMEOUT:
	  alert("The request to get user location has aborted as it has taken too long.");
	  break;
	case positionError.POSITION_UNAVAILABLE:
	  alert("Location information is not available.");
	  break;
	case positionError.PERMISSION_DENIED:
	  alert("Permission to share location information has been denied!");
	  location.reload();
	  break;
	default:
	  alert("An unknown error occurred.");
  }
}