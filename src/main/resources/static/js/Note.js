var titleInput = $("#titleInput");
var noteTextArea = $("#noteTextArea");
var category = $("#categorySelect");
var remindDateInput = $("#remindDateInput");

titleInput.on("input", () => {
    validate(titleInput)
});

noteTextArea.on("input", () => {
    validate(noteTextArea)
});


remindDateInput.on("change", () => {
    let strDate = remindDateInput.val();
    if (strDate.length > 0 ) {
        remindDateInput.removeClass("is-invalid");
        remindDateInput.addClass("is-valid");
    }

})

function validate(textInput){
    if (textInput.val().length > 0){
        textInput.removeClass("is-invalid");
        textInput.addClass("is-valid");
    }
    else {
        textInput.removeClass("is-valid");
        textInput.addClass("is-invalid");
    }
}