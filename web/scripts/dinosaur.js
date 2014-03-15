
$(function() {

    $.get("/", function() {
        $("#output").append("&#10;Please enter the name of your dinosaur");
    })

    $("#submitButton").click(function() {
        var data = {};
        data.name = $("#input").val();
        $.ajax({
            url: "game/start",
            type: "POST",
            data: data,
            contentType: "application/json",
            dataType: "json",
            success: function(data) {
                $("#output").append("&#10;" + data.greeting);
                $("#stats").html(data.stats);
            }
        });
    });
});
