function getApartments(house) {
    $.ajax('./apartment', {
        method: 'get',
        error: function () {
            alert('Нет связи с сервером')
        },
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
            error: function () {
                alert('Нет связи с сервером')
            },
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

function editApartment() {
    if ($('#apartment_id').val() == '') {
        alert('Выберите квартиру которую требуется поменять');
        return;
    }
    $('.modal-title').empty().append('Изменить привязку квартиры');
    var body = $('.modal-body').empty();
    body.append('<p>Выберите улицу куда перенести квартиру ' + $('#apartment_id option:selected').text() + ' c адреса ' + $('#street_id option:selected').text() + ' ' + $('#house_id option:selected').text() + '</p>');
    $('#street_id').clone().appendTo(body);
    $('#house_id').clone().appendTo(body);
    body.append($('<button type="button" class="btn btn-default" onclick="apartmentBDEdit()" data-dismiss="modal">Изменить</button>'));
    $('#myModal').modal('show');
}

function apartmentBDEdit() {
    var house = $('.modal-body').find('#house_id').val();
    var apartment = $('#apartment_id').val();
    $.ajax('./apartment', {
        method: 'post',
        error: function () {
            alert('Нет связи с сервером')
        },
        data: {
            apartment: apartment,
            house: house
        },
        complete: function () {
            getStreets();
            clearHouse();
            clearApartment()
        }
    });
}

$(document).ready(function () {
    $('.modal-body').on('change', '#street_id', function () {
        getApartHouse(this.value)
    });
});

function getApartHouse(street) {
    $.ajax('./house', {
        method: 'get',
        error: function () {
            alert('Нет связи с сервером')
        },
        data: {
            street: street
        },
        complete: function (data) {
            var houses = JSON.parse(data.responseText);
            addApartEditHouse(houses)
        }
    });
}

function addApartEditHouse(houses) {
    var house_id = $('.modal-body').find('#house_id');
    house_id.empty().append(($('<option>', {
        value: '',
        text: 'Выбрать дом'
    })));
    houses.forEach(function (house) {
        house_id.append($('<option>', {
            value: house.id,
            text: house.name
        }))
    })
}