$(document).ready(function() {
    $(".compulsory").focusout(function() {
        // Each input contains attribute: data-qid="#"
        var qid = $(this).data("qid");
        
        // Append "has-error" class to the input if no value has been entered.
        // This will only be apply to text field or text area, as other inputs always contain a value.
        if ($(this).val() != "") {
            $(this).closest("div").removeClass('has-error');
            $(this).next(".error-msg").remove();
        } else {
            $(this).closest("div").addClass('has-error');
            if ($(this).next(".error-msg").length == 0)
                $('<div class="error-msg">Please fill in the blank.</div>').insertAfter($(this));
        }

        // Toggle "incomplete" class to the question's link on sidebar.
        if ($(this).val() != "") {
            $("#"+qid+"Link").removeClass("incomplete");
        } else {
            $("#"+qid+"Link").addClass("incomplete");
        }
    });
});