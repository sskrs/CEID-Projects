$("#maintenance").submit(function (event) {
  var clickedLocation = $("#clickedLocation").val();
  var time = $("#time").val();
  var radius = $("#radius").val();
  var values = "clickedLocation=" + clickedLocation + "&time=" + time + "&radius=" + radius;

  $.ajax({
    type: "POST",
    url: "maintenance.php",
    data: values,
    // if sent
    success: function () {
      drawMap();
      draw();
    },
    error: function () {
      alert("Under construction!");
    }
  });
  event.preventDefault();
});

var currentTime = new Date().toLocaleTimeString(); 
$(document).ready(function () {
  $('.timepicker').timepicker({
    timeFormat: 'HH:mm ',
    interval: 60,
    minTime: '00:00',
    maxTime: '23.00 pm',
    defaultTime: currentTime,
    startTime: '10:00',
    dynamic: false,
    dropdown: false,
    scrollbar: false
  });
});

$("#calcDemand").submit(function (event) {
  var time = $("#timepicker").val();
  var values = "time=" + time;

  $.ajax({
    type: "POST",
    url: "runSim.php",
    data: values,
    // if sent
    success: function () {
      alert("Success!");
    },
    error: function () {
      alert("Κάτι πήγε στραβά!");
    }
  });
  event.preventDefault();
});

$(document).ready(function () {
  $.ajax({
    url: "runSim.php",
    success: function () {
      drawMap();
    },
    error: function () {
      console.log("Error!");
    }
  });
});