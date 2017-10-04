function getHouse(street) {
    $.ajax('./house', {
        method: 'get',
        data: {
            street: street
        },
        complete: function (data) {
            var houses = JSON.parse(data.responseText);
            addHouses(houses)
        }
    });
}

function addHouses(houses) {
    houses.forEach(function (house) {
        clearHouse();
        $('#house_id').append($('<option>', {
            value: house.id,
            text: house.name
        }))
    });
}

$(document).ready(function () {
    $('#street_id').on('change', function () {
        var valueSelected = this.value;
        if (valueSelected == '') {
            clearHouse()
        } else {
            getHouse(valueSelected)
        }
    });
});

function clearHouse() {
    $('#house_id').empty().append(($('<option>', {
        value: '',
        text: 'Выбрать дом'
    })));
}