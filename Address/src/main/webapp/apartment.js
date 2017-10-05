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
    clearApartment();
    apartments.forEach(function (apartment) {
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

function modalApartment() {
    if ($('#house_id').val() == '') {
        alert('Выберите дом куда будет добавлена новая квартира');
        return;
    }
    $('.modal-title').empty().append('Добавить новую квартиру');
    var body = $('.modal-body').empty();
    body.append('<p>1. Написать номер квартиры</p>')
        .append('<p>2. Нажать кнопку добавить</p>')
        .append('<p>Новая квартира будет добавлена по адресу: ' + $('#street_id option:selected').text() + $('#house_id option:selected').text() + '</p>')
        .append($('<input id="newApartment" type="text" class="form-control" placeholder="Введите номер новой квартиры">'))
        .append($('<button type="button" class="btn btn-default" onclick="addNewApartment()" data-dismiss="modal">Добавить</button>'));
    $('#myModal').modal('show');
}

function addNewApartment() {
    var house = $('#house_id').val();
    var apartment = $('.modal-body').find('#newApartment').val();
    if (apartment != '' && house != '') {
        $.ajax('./apartment', {
            method: 'post',
            data: {
                name: apartment,
                house: house
            },
            complete: function () {
                getApartments(house)
            }
        });
    } else {
        alert('Введите название нового дома')
    }
}