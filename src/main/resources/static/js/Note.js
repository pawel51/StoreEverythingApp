// var titleInput = $("#titleInput");
// var noteTextArea = $("#noteTextArea");
// var category = $("#categorySelect");
// var remindDateInput = $("#remindDateInput");
//
// titleInput.on("input", () => {
//     validate(titleInput, 20, 3)
// });
//
// noteTextArea.on("input", () => {
//     validate(noteTextArea, 500, 3)
// });
//
//
// remindDateInput.on("change", () => {
//     let strDate = remindDateInput.val();
//     if (strDate.length > 0 ) {
//         remindDateInput.removeClass("is-invalid");
//         remindDateInput.addClass("is-valid");
//     }
//
// })
//
// function validate(textInput, max, min){
//     if (textInput.val().length >= min && textInput.val().length <= max){
//         textInput.removeClass("is-invalid");
//         textInput.addClass("is-valid");
//     }
//     else {
//         textInput.removeClass("is-valid");
//         textInput.addClass("is-invalid");
//     }
// }