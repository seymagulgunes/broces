function trackCiz() {
    debugger;
    var trackSayisi = jQuery("#trackSayisi").val();
    jQuery.ajax({
        type: "POST",
        url: "./broces/trackCiz.ajax",
        data: {trackSayisi: trackSayisi},
        dataType: "json",
        success: function (response) {
            var jsonData = response;
            if (jsonData.success) {
                $("#trackImage").attr("src", jsonData.imageData);
                $("#trackImage").css("display", "block");
            }
        }
    });
}

function trackIsaretle() {
    debugger;
    var trackSayisi = jQuery("#trackSayisi").val();
    var minHex = jQuery("#minHex").val();
    var maxHex = jQuery("#maxHex").val();
    jQuery.ajax({
        type: "POST",
        url: "./broces/trackCiz.ajax",
        data: {trackSayisi: trackSayisi, min: minHex, max: maxHex},
        dataType: "json",
        success: function (response) {
            var jsonData = response;
            if (jsonData.success) {
                $("#trackImage").attr("src", jsonData.imageData);
                $("#trackImage").css("display", "block");
            }
        }
    });
}