$(document).ready(function () {
    var myWidget = cloudinary.createUploadWidget(
        {

            cloudName: 'df4sipfvc',
            uploadPreset: 'i7byqulq'

        }, function (error, result) {
            if (!error && result && result.event === "success") {
                $("#preview").attr("src", result.info.secure_url);
                $("input[name='avatar']").val(result.info.secure_url);
                $("input[name='thumbnail']").val(result.info.secure_url);
                $("input[name='photos']").val(result.info.secure_url);
            }
        }
    )
    $("#upload_widget").click(function(){
        myWidget.open();
    });
})
