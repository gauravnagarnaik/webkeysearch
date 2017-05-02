$(document).ready(function () {

    $("#btn-search").click(function (event) {

           //stop submit the form, post it manually.
           event.preventDefault();
           $("#feedback").html("");
           fire_ajax_submit();
    });

    $("#btn-reverse-search").click(function (event) {

               //stop submit the form, post it manually.
               event.preventDefault();
               $("#feedback").html("");
               fire_ajax_reverse_submit();
        });



});

function fire_ajax_submit() {

    var search = {}
    search["label"] = $("#label").val();

    $("#btn-search").prop("disabled", true);
    $("#btn-reverse-search").prop("disabled", true);


    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4 align=\"center\"><pre>" + JSON.stringify(data.translation) + "</pre></h4>";
            $('#feedback').html(json);


            console.log("SUCCESS : ", data);

            $("#btn-search").prop("disabled", false);
            $("#btn-reverse-search").prop("disabled", false);

        },
        error: function (e) {
                        console.log("ERROR : ", e);
                        $("#btn-search").prop("disabled", false);
                        $("#btn-reverse-search").prop("disabled", false);
        }
    });

}

function fire_ajax_reverse_submit() {
    var search = {}
        search["label"] = $("#label").val();

        $("#btn-search").prop("disabled", true);
        $("#btn-reverse-search").prop("disabled", true);


        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/searchReverse",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4 align=\"center\"><pre>" + JSON.stringify(data.translation) + "</pre></h4>";
                $('#feedback').html(json);


                console.log("SUCCESS : ", data);

                $("#btn-search").prop("disabled", false);
                $("#btn-reverse-search").prop("disabled", false);

            },
            error: function (e) {
                console.log("ERROR : ", e);
                $("#btn-search").prop("disabled", false);
                $("#btn-reverse-search").prop("disabled", false);
            }
        });

}