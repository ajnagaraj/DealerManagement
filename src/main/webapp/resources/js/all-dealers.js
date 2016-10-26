var search = $('#search-dealer');

search.focus();

search.keyup(function (event) {
    var searchValue = search.val();

    var searchEmpty = event.keyCode == 8 && searchValue == '';
    if (searchEmpty) {
        $('div[id^="id-"]').show();
    }

    console.log(event.which);

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
