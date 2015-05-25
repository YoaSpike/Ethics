$(".init-hidden").each(function() {
    var qid = $(this).attr("id");
    $(this).hide();
    $("#"+qid+"Link").css("text-decoration", "line-through");
});


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

    $(".sidebar-main").hover(function() {
        $(this).children(".sidebar-sectq").slideDown(200);
    }, function() {
        if (!$(this).hasClass("sidebar-main-active")) {
            $(this).children(".sidebar-sectq").slideUp(200);
        }
    });
});

function showBody(e) {
    $(e).siblings(".panel-body").slideToggle(200);
}

/**
 * Function that only called be main questions to unhide child questions.
 * This will show() the child question with class ".init-hidden", as well as adding text-decoration
 * to the representing question links on the sidebar. (decided by toShow)
 * 
 * @param  {[type]} e      Element that calls the function. (should be a radio button of main-q)
 * @param  {[type]} toShow Boolean value indicates to show child questions or not.
 * @return {[type]}        None
 */
function toggleSub(e, toShow) {

    $(e).closest('.main-q-wrapper').children(".sub-q-wrapper").each(function() {
        var qid = $(this).attr("id");

        $(this).toggle(toShow);
        if (toShow) {
            $("#"+qid+"Link").css("text-decoration", "none");
        } else {
            $("#"+qid+"Link").css("text-decoration", "line-through");
        }
    });
}

function toggleTA(e, toShow) {
    console.log("Toggling " + $(e).closest(".form-group").attr("id"));
    $(e).closest(".form-group").children(".ta-group").toggle(toShow);
}

function switchDesc(e, yn) {
var tagroup = $(e).closest(".form-group").children(".ta-group");
tagroup.children(".main-q-desc").hide();
tagroup.children("[name="+yn+"]").show();
}