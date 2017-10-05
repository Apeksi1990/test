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
    clearHouse();
    houses.forEach(function (house) {
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

function modalHouse() {
    if ($('#street_id').val() == '') {
        alert('Выберите улицу куда будет добавлен новый дом')
        return;
    }
    $('.modal-title').empty().append('Добавить новый дом');
    var body = $('.modal-body').empty();
    body.append('<p>1. Написать номер дома</p>')
        .append('<p>2. Нажать кнопку добавить</p>')
        .append('<p>Новый дом будет добавлен на улицу: ' + $('#street_id option:selected').text() + '</p>')
        .append($('<input id="newHouse" type="text" class="form-control" placeholder="Введите номер нового дома">'))
        .append($('<button type="button" class="btn btn-default" onclick="addNewHouse()" data-dismiss="modal">Добавить</button>'));
    $('#myModal').modal('show');
}

function addNewHouse() {
    var street = $('#street_id').val();
    var house = $('.modal-body').find('#newHouse').val();
    if (street != '' && house != '') {
        $.ajax('./house', {
            method: 'post',
            data: {
                name: house,
                street: street
            },
            complete: function () {
                getHouse(street);
            }
        });
    } else {
        alert('Введите название нового дома')
    }
}