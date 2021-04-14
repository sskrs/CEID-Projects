//Perform an asynchronous HTTP (Ajax) request.
$("#deleteButton").click(function () {
  $.ajax({
    url: 'deletedb.php',
    method: "post",
    success: function (data) {
      alert('Η ΒΔ διαγράφτηκε επιτυχώς!');
      $('#deleteModal').modal('hide');
      document.location.reload(true);
      mymap.removeLayer(graymap);
    }

  });
});

//st html exoume prosthesei jquery gia css kai js
//twrinh wra internet
var currentTime = new Date().toLocaleTimeString();
//https://timepicker.co gia to format ths method
$(document).ready(function () {
  $('.timepicker').timepicker({
    timeFormat: 'h:mm p',
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

// Add block info
$(document).ready(function () {
  $("#mapid").click(function () {
    $("#manageBlock").submit(function (event) {
      var form = $(this);
      var url = form.attr('action');
      $.ajax({
        type: 'POST',
        url: 'manageBlock.php',
        data: form.serialize(),
        // if sent
        success: function (data) {
          $("#blockAdded").html("Done");
        }
      });
      event.preventDefault();
    });
  });
});

//Calculate demand for given time
$("#calcDemand2").submit(function (event) {
  var time = $("#timepicker2").val();
  var values = "time=" + time;

  $.ajax({
    type: "POST",
    url: "runSim.php",
    data: values,
    success: function () {
      draw();
    },
    error: function () {
      alert("Κάτι πήγε στραβά!");
    }
  });
  event.preventDefault();
});


$("form#uploadForm").submit(function (e) {
  e.preventDefault();
  var formData = new FormData(this);

  $.ajax({
    url: 'upload.php',
    type: 'POST',
    data: formData,
    success: function (data) {
      alert(data);
      document.location.reload(true);
    },
    cache: false,
    contentType: false,
    processData: false
  });


});

$("#removeMin").click(function () {
  var mins = $("#defMins").val();
  var timeString = $("#timepicker2").val();
  var timeSplit = timeString.split(':');
  var hours = parseInt(timeSplit[0]);
  var minutes = parseInt(timeSplit[1]);

  //convert to mins
  startTime = hours * 60 + minutes;
  endTime = startTime - mins;

  let h = Math.floor(endTime / 60);
  let m = endTime % 60;
  h = h < 10 ? '0' + h : h;
  m = m < 10 ? '0' + m : m;

  time = h + ':' + m;
  $("#timepicker2").val(time);
});

$("#addMin").click(function () {
  var mins = $("#defMins").val();
  var timeString = $("#timepicker2").val();
  var timeSplit = timeString.split(':');
  var hours = parseInt(timeSplit[0]);
  var minutes = parseInt(timeSplit[1]) + parseInt(mins);
  hours += Math.floor(minutes / 60);
  while (hours >= 24) {
    hours -= 24;
  }
  minutes = minutes % 60;

  var time = ('0' + hours).slice(-2) + ':' + ('0' + minutes).slice(-2);
  $("#timepicker2").val(time);


});
