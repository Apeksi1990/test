$(function () {
    $.ajax('./street', {
        method: 'get',
        complete: function (data) {
            var streets = JSON.parse(data.responseText);
            addStreets(streets)
        }
    });
});

function addStreets(streets) {
    streets.forEach(function (street) {
        $('#street_id').append($('<option>', {
            value: street.id,
            text: street.name
        }))
    });
}