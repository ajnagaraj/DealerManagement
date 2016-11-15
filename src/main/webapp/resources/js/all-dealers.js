var search = $('#search-dealer');
var nextId = 1;

search.focus();

search.keyup(function (event) {
    var searchValue = search.val();

    var searchEmpty = event.keyCode == 8 && searchValue == '';
    if (searchEmpty) {
        $('div[id^="id-"]').show();
    }

    var enterKey = event.which == 13;
    if (enterKey) {
        // If blank show all dealers and return
        if (searchValue == '') {
            $('div[id^="id-"]').show();
            return;
        }

        // If value is not blank, hide all dealers
        $('div[id^="id-"]').hide();

        var dealers = $("div[id^='id-']");

        dealers.each(function () {
            var dealer = $(this);
            var content = dealer.children();
            content.each(function () {
                var thumbnailContents = $(this).children().text();

                var regexSearchValue = new RegExp(searchValue, "i");
                var matches = thumbnailContents.match(regexSearchValue);
                console.log("Matches: " + matches);

                if (matches != null) {
                    dealer.show();
                }
            })
        });

        search.blur();
    }
});


var validationErrors;
var shouldRefreshDealers = false;

$("#create-dealer").click(function () {
    initializeValidationErrors();
    shouldRefreshDealers = false;

    var formData = getFormData();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(formData),
        url: "http://localhost:9595/dealer-management/dealers",
        dataType: 'json',
        timeout: 100000,
        success: function (result) {
            $("#create-status").text(result.name + " Saved");
            shouldRefreshDealers = true;
            initializeTextboxValues();
        },
        error: function (error) {
            validationErrors = JSON.parse(error.responseText);
            setValidationErrors(validationErrors);
        }
    });
});

$("#update-dealer").click(function () {
    initializeEditValidationErrors();
    shouldRefreshDealers = false;

    var formData = getEditFormData();

    console.log("Stringify: " + JSON.stringify(formData));

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(formData),
        url: "http://localhost:9595/dealer-management/dealers",
        dataType: 'json',
        timeout: 100000,
        success: function (result) {
            console.log("Edit Result: " + result.name);
            shouldRefreshDealers = true;
            $("#edit-dealer-modal").modal('hide');
            refreshDealers();
        },
        error: function (error) {
            validationErrors = JSON.parse(error.responseText);
            setEditValidationErrors(validationErrors);
        }
    });
});

var getFormData = function () {
    var formData = {
        "name": $("#inputName").val(),
        "email": $("#inputEmail").val(),
        "phone": $("#inputPhone").val(),
        "associate": $("#inputAssociate").val(),
        "address": {
            "line": $("#inputStreet").val(),
            "city": $("#inputCity").val(),
            "postCode": $("#inputPostcode").val(),
            "zone": $("#inputZone").val(),
            "country": $("#inputCountry").val()
        },
        "products": []
    };

    return formData;
};

var getEditFormData = function () {
    var formData = {
        "id": $("#editId").val(),
        "name": $("#editName").val(),
        "email": $("#editEmail").val(),
        "phone": $("#editPhone").val(),
        "associate": $("#editAssociate").val(),
        "address": {
            "line": $("#editStreet").val(),
            "city": $("#editCity").val(),
            "postCode": $("#editPostcode").val(),
            "zone": $("#editZone").val(),
            "country": $("#editCountry").val()
        },
        "products": []
    };

    return formData;
};

function initializeTextboxValues() {
    $("#inputName").val("");
    $("#inputEmail").val("");
    $("#inputPhone").val(""),
        $("#inputAssociate").val("");
    $("#inputStreet").val("");
    $("#inputCity").val("");
    $("#inputPostcode").val("");
    $("#inputZone").val("");
    $("#inputCountry").val("");
}

var initializeValidationErrors = function () {
    $("#name-error").text("");
    $("#email-error").text("");
    $("#phone-error").text("");
    $("#associate-error").text("");
    $("#street-error").text("");
    $("#city-error").text("");
    $("#postcode-error").text("");
    $("#country-error").text("");
    $("#zone-error").text("");
}

var initializeEditValidationErrors = function () {
    $("#edit-name-error").text("");
    $("#edit-email-error").text("");
    $("#edit-phone-error").text("");
    $("#edit-associate-error").text("");
    $("#edit-street-error").text("");
    $("#edit-city-error").text("");
    $("#edit-postcode-error").text("");
    $("#edit-country-error").text("");
    $("#edit-zone-error").text("");
}

// Function to set all the validation errors to their labels
var setValidationErrors = function (validationErrors) {
    // Name error, if any
    if (validationErrors.nameError !== null) {
        $("#name-error").text(validationErrors.nameError);
    }

    // Email error, if any
    if (validationErrors.emailError !== null) {
        $("#email-error").text(validationErrors.emailError);
    }

    // Phone error, if any
    if (validationErrors.phoneError !== null) {
        $("#phone-error").text(validationErrors.phoneError);
    }

    // Associate error, if any
    if (validationErrors.associateError !== null) {
        $("#associate-error").text(validationErrors.associateError);
    }

    // Street error, if any
    if (validationErrors.addressError.streetError !== null) {
        $("#street-error").text(validationErrors.addressError.streetError);
    }

    // City error, if any
    if (validationErrors.addressError.cityError !== null) {
        $("#city-error").text(validationErrors.addressError.cityError);
    }

    // Postcode error, if any
    if (validationErrors.addressError.postcodeError !== null) {
        $("#postcode-error").text(validationErrors.addressError.postcodeError);
    }

    // Country error, if any
    if (validationErrors.addressError.countryError !== null) {
        $("#country-error").text(validationErrors.addressError.countryError);
    }

    // Zone error, if any
    if (validationErrors.addressError.zoneError !== null) {
        $("#zone-error").text(validationErrors.addressError.zoneError);
    }
};

// Function to set all the validation errors to their labels
var setEditValidationErrors = function (validationErrors) {
    // Name error, if any
    if (validationErrors.nameError !== null) {
        $("#edit-name-error").text(validationErrors.nameError);
    }

    // Email error, if any
    if (validationErrors.emailError !== null) {
        $("#edit-email-error").text(validationErrors.emailError);
    }

    // Phone error, if any
    if (validationErrors.phoneError !== null) {
        $("#edit-phone-error").text(validationErrors.phoneError);
    }

    // Associate error, if any
    if (validationErrors.associateError !== null) {
        $("#edit-associate-error").text(validationErrors.associateError);
    }

    // Street error, if any
    if (validationErrors.addressError.streetError !== null) {
        $("#edit-street-error").text(validationErrors.addressError.streetError);
    }

    // City error, if any
    if (validationErrors.addressError.cityError !== null) {
        $("#edit-city-error").text(validationErrors.addressError.cityError);
    }

    // Postcode error, if any
    if (validationErrors.addressError.postcodeError !== null) {
        $("#edit-postcode-error").text(validationErrors.addressError.postcodeError);
    }

    // Country error, if any
    if (validationErrors.addressError.countryError !== null) {
        $("#edit-country-error").text(validationErrors.addressError.countryError);
    }

    // Zone error, if any
    if (validationErrors.addressError.zoneError !== null) {
        $("#edit-zone-error").text(validationErrors.addressError.zoneError);
    }
};

function refreshDealers() {
    if (shouldRefreshDealers) {
        console.log("Refreshing..");
        document.location.reload(true);
    }
}

var onEditDealer = function (id) {
    console.log("Id: " + id);
    $.ajax({
        type: "GET",
        accept: "application/json",
        url: "http://localhost:9595/dealer-management/dealers/" + id,
        timeout: 100000,
        success: function (result) {
            bindDealer(result);
        },
        error: function (error) {
            validationErrors = JSON.parse(error.responseText);
            console.log(validationErrors);
        }
    });
}

var bindDealer = function (dealer) {
    $(".modal-title").text(dealer.name);
    $("#editId").val(dealer.id);
    $("#editId").hide();
    $("#editName").val(dealer.name);
    $("#editEmail").val(dealer.email);
    $("#editPhone").val(dealer.phone);
    $("#editAssociate").val(dealer.associate);
    $("#editStreet").val(dealer.address.line);
    $("#editCity").val(dealer.address.city);
    $("#editPostcode").val(dealer.address.postCode);
    $("#editZone").val(dealer.address.zone);
    $("#editCountry").val(dealer.address.country);
}


var getAllNotesForDealer = function (id) {
    var notes;

    $.ajax({
        type: "GET",
        accept: "application/json",
        url: "/dealer-management/notes/dealer/" + id,
        timeout: 100000,
        success: function (result) {
            $.each($("#notes").children(), function () {
                this.remove();
            });

            $.each(result, function () {
                console.log(this);
                $("#notes").append(formEditNote(this.id, this.time, this.text, this.dealer.id));
            });
        },
        error: function (error) {
            console.log(error);
            validationErrors = JSON.parse(error.responseText);
            setValidationErrors(validationErrors);
        }
    });
}

var formEditNote = function (noteId, time, text, dealerId) {
    nextId += 1;
    var textAreaId = "text-area-" + nextId;
    var timeId = "time-id-" + nextId;

    var note = "<div class=\"col-xs-12\" id=\"div-dealer-note\" class=\"form-group\"><div class=\"time col-xs-10\"><label id=\"" + noteId + "\" hidden=\"true\">" + noteId + "</label><label id=\"" + timeId + "\" for=\"text-area\">" + time + "</label></div><div class=\"note-save col-xs-2\"><a onclick=\"editText('" + noteId + "', '" + textAreaId + "', '" + timeId + "', '" + dealerId + "')\" href=\"#\"><i class=\"fa fa-pencil\" aria-hidden=\"true\"></i></a></div><textarea class=\"form-control\" id=\"" + textAreaId + "\" rows=\"3\">" + text + "</textarea></div>";

    return note;
}

var formNewNote = function (time, text) {
    nextId += 1;
    var textAreaId = "text-area-" + nextId;
    var timeId = "time-id-" + nextId;

    var note = "<div class=\"col-xs-12\" id=\"div-dealer-note\" class=\"form-group\"><div class=\"time col-xs-10\"><label id=\"" + timeId + "\" for=\"text-area\">" + time + "</label></div><div class=\"note-save col-xs-2\"><a onclick=\"saveText('" + textAreaId + "')\" href=\"#\"><i class=\"fa fa-floppy-o\" aria-hidden=\"true\"></i></a></div><textarea class=\"form-control\" id=\"" + textAreaId + "\" rows=\"3\">" + text + "</textarea></div>";

    return note;
}


var removeFirstNote = function () {
    var notes = $("#notes").children();

    if (notes.length !== 0) {
        notes.get(0).remove();
    }
}

var addNewNote = function () {
    var today = new Date();

    var time = today.getHours() + ":" + today.getMinutes() + ", " + today.toDateString();

    $("#notes").prepend(formNewNote(time, ""));
}

var timeNowFormatted = function () {
    var today = new Date();

    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds() + ":" + today.getMilliseconds();

    var date = today.toDateString();

    console.log(time + ", " + date);
}


var dealerNoteSearch = $("#search-dealer-note");

dealerNoteSearch.keyup(function (event) {
    var searchValue = dealerNoteSearch.val();

    var searchEmpty = event.keyCode == 8 && searchValue == '';
    if (searchEmpty) {
        $("div[id^='div-dealer-note']").show();
    }

    var enterKey = event.which == 13;
    if (enterKey) {
        // If blank show all dealers and return
        if (searchValue == '') {
            console.log("empty");
            $('div[id^="div-dealer-note"]').show();
            return;
        }

        // If value is not blank, hide all dealers
        $('div[id^="div-dealer-note"]').hide();

        var dealerNotes = $("div[id^='div-dealer-note']");

        dealerNotes.each(function () {
            var dealerNote = $(this);

            var noteContent = dealerNote.text();
            console.log("Dealer note content: " + noteContent);

            var regexSearchValue = new RegExp(searchValue, "i");
            var matches = noteContent.match(regexSearchValue);

            console.log("Matches: " + matches);

            if (matches != null) {
                dealerNote.show();
            }
        });

        dealerNoteSearch.blur();
    }
});

var editText = function (noteId, textAreaId, timeId, dealerId) {
    textAreaId = "#" + textAreaId;
    $(textAreaId).css("color", "#F39C12");
    $(textAreaId).text($(textAreaId).val());
    
    var note = {
        "id": noteId,
        "time": $("#" + timeId).text(),
        "text": $(textAreaId).val(),
        "dealer": {
            "id": dealerId
        }
    };
    
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(note),
        url: "/dealer-management/notes",
        dataType: 'json',
        timeout: 100000,
        success: function (result) {
            console.log("edit note result: " + result);
        },
        error: function (error) {
            console.log("edit note error: " + error.responseText);
        }
    });
}

var saveText = function (textAreaId) {
    textAreaId = "#" + textAreaId;
    $(textAreaId).css("color", "#F39C12");
    $(textAreaId).text($(textAreaId).val());
    console.log($(textAreaId).val());
}
