var x = document.getElementById("demo");
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
}
function getLatPosition(){
	return navigator.geolocation.getCurrentPosition(showPosition).coords.latitude;
}

function showPosition(position) {
    x.innerHTML = "Latitude: " + position.coords.latitude + 
    "<br>Longitude: " + position.coords.longitude; 
}
//for form page
function setLocation(){
	 getLocation();
	 if(position.coords.longitude == -73.201157 && position.coords.latitude == 44.478283){
		 var element = document.getElementById("location")
		 element.value = "Waterman"
	 }
}