function getApartments(house) {
    $.ajax('./apartment', {
        method: 'get',
        data: {
            house: house
        },
        complete: function (data) {
            var apartments = JSON.parse(data.responseText);
            addApartments(apartments)
        }
    });
}

function addApartments(apartments) {
    apartments.forEach(function (apartment) {
        clearApartment();
        $('#apartment_id').append($('<option>', {
            value: apartment.id,
            text: apartment.name
        }))
    });
}

$(document).ready(function () {
    $('#house_id').on('change', function () {
        var valueSelected = this.value;
        if (valueSelected == '') {
            clearApartment()
        } else {
            getApartments(valueSelected)
        }
    });
    $('#street_id').on('change', function () {
        clearApartment()
    });
});

function clearApartment() {
    $('#apartment_id').empty().append(($('<option>', {
        value: '',
        text: 'Выбрать квартиру'
    })));
}