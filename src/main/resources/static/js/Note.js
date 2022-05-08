var titleInput = $("#titleInput");

titleInput.on("change", () => {
    if (titleInput.val().length > 0){
        titleInput.removeClass("is-invalid");
        titleInput.addClass("is-valid");
    }
});