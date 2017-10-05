function getStreets() {
    $.ajax('./street', {
        method: 'get',
        complete: function (data) {
            var streets = JSON.parse(data.responseText);
            addStreets(streets)
        }
    });
}
window.onload = getStreets();

function addStreets(streets) {
    clearStreet();
    streets.forEach(function (street) {
        $('#street_id').append($('<option>', {
            value: street.id,
            text: street.name
        }))
    });
}

function modalStreet() {
    $('.modal-title').empty().append('Добавить новую улицу');
    var body = $('.modal-body').empty();
    body.append('<p>1. Написать название улицы</p>')
        .append('<p>2. Нажать кнопку добавить</p>')
        .append($('<input id="newStreet" type="text" class="form-control" placeholder="Введите название новой улицы">'))
        .append($('<button type="button" class="btn btn-default" onclick="addNewStreet()" data-dismiss="modal">Добавить</button>'));
    $('#myModal').modal('show');
}

function addNewStreet() {
    var street_name = $('.modal-body').find('#newStreet').val();
    if (street_name != '') {
        $.ajax('./street', {
            method: 'post',
            data: {
                name: street_name
            },
            complete: function () {
                getStreets();
            }
        });
    } else {
        alert('Введите название новой улицы')
    }

}

function clearStreet() {
    $('#street_id').empty().append(($('<option>', {
        value: '',
        text: 'Выбрать улицу'
    })));
}