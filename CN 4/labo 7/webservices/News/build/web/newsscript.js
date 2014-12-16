var URL = "http://localhost:8080/News/rest/news/";

if (typeof jQuery === 'undefined') {
    log("jQuery not loaded!");
}

$(document).ready(function()
{
    $("#submitButton").on("click", postNewsItem);
    $.getJSON(URL, fillNewsItems);
});

function log(msg)
{
    if (window.console) {
        console.log(msg);
    }
}

function makeParamString(params)
{
    if (params)
    {
        var str = [];
        for (var p in params)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(params[p]));
        return str.join("&");
    }
    else
        return "";
}


function createNewsItemElement(item)
{
    var div = $("<div>").attr("id", "entry" + item.id);

    $("<span>")
            .attr("id", "title" + item.id)
            .html(item.title)
            .appendTo(div);

    $("<input>")
            .attr("type", "button")
            .attr("id", "toggle" + item.id)
            .attr("value", "+")
            .on("click", function() {
        toggleDetails(item.id);
    })
            .appendTo(div);

    $("<div>")
            .attr("id", "details" + item.id)
            .appendTo(div);

    return div;
}

function fillNewsItems(data) {
    var listNode = $("#entries").empty();
    $.each(data, function(i, item) {
        log("loaded " + item.id + ": " + item.title);
        var itemNode = createNewsItemElement(item);
        listNode.prepend(itemNode);
    }
    );
}


function toggleDetails(eid)
{
    log("toggling " + eid);
    //var el = $("#entry" + eid);
    var input = $("#toggle" + eid);
    if (input.val() === '+')
    {
        input.val('-');
        loadNewsItemDetails(eid);
    }
    else
    {
        input.val('+');
        $("#details" + eid).empty();
    }
}


function loadNewsItemDetails(eid)
{
    log("loading details " + eid);
    var detailsNode = $("#details" + eid).empty();
    $.getJSON(URL + eid, function(data) {
        log("loading details ok");
        detailsNode.append(createDetailsElement(eid, data));
    });
}

function createDetailsElement(eid, item)
{
    return $("<table>")
            .append($("<tr>")
            .append($("<td>")
            .html("Date:")
            )
            .append($("<td>")
            .html(Date(item.publishDate))
            )
            )
            .append($("<tr>")
            .append($("<td>")
            .html("Author:")
            )
            .append($("<td>")
            .append($("<input>")
            .attr("id", "authorTF" + eid)
            .attr("type", "text")
            .attr("value", item.author)
            .css("border", "none")
            .attr("disabled", "true")
            )
            )
            )
            .append($("<tr>")
            .append($("<td>")
            .html("Title:")
            )
            .append($("<td>")
            .append($("<input>")
            .attr("id", "titleTF" + eid)
            .attr("type", "text")
            .attr("value", item.title)
            .css("border", "none")
            .attr("disabled", "true")
            )
            )
            )
            .append($("<tr>")
            .append($("<td>")
            .html("Message:")
            )
            .append($("<td>")
            .append($("<input>")
            .attr("id", "messageTF" + eid)
            .attr("type", "text")
            .attr("size", 100)
            .attr("value", item.message)
            .css("border", "none")
            .attr("disabled", "true")
            )
            )
            )
            .append($("<tr>")
            .append($("<td>"))
            .append($("<td>")
            .append($("<input>")
            .attr("id", "remove" + eid)
            .attr("type", "button")
            .attr("value", "Remove")
            .click(function() {
        removeNewsItem(eid);
    })
            )
            .append($("<input>")
            .attr("id", "edit" + eid)
            .attr("type", "button")
            .attr("value", "Edit")
            .click(function() {
        editNewsItem(eid);
    })
            )
            .append($("<input>")
            .attr("id", "apply" + eid)
            .attr("type", "button")
            .attr("value", "Apply")
            .css("display", "none")
            .click(function() {
        applyEdit(eid);
    })
            )
            .append($("<input>")
            .attr("id", "cancel" + eid)
            .attr("type", "button")
            .css("display", "none")
            .attr("value", "Cancel")
            .click(function() {
        cancelEdit(eid);
    })
            )
            )
            );
}

function removeNewsItem(eid)
{
    $.ajax({type: "DELETE", url: URL + eid, success: $.getJSON(URL, fillNewsItems)});
}

function postNewsItem()
{
    var author = $("#newEntry #authorTF").val();
    var title = $("#newEntry #titleTF").val();
    var message = $("#newEntry #messageTF").val();
    var newsitem = {author: author, title: title, message: message};

    log("sending " + author + ", " + title + ", " + message);
    $.ajax({type:"POST", url: URL, contentType: "application/json", data: JSON.stringify(newsitem) 
        , success: loadNewsItems, dataType: "json"});
    $("#authorTF, #titleTF, #messageTF", "#newEntry").val("");
}

function loadNewsItems() {
    $.getJSON(URL, fillNewsItems);
}
    

function editNewsItem(eid)
{
    $("#authorTF" + eid + ", #titleTF" + eid + ", #messageTF" + eid)
            .removeAttr("disabled")
            .css("border", "");

    $("#edit" + eid).css("display", "none");
    $("#apply" + eid).css("display", "");
    $("#cancel" + eid).css("display", "");
}

function applyEdit(eid)
{
    log("applying " + eid);
    var author = $("#authorTF" + eid).val();
    var title = $("#titleTF" + eid).val();
    var message = $("#messageTF" + eid).val();
    var newsitem = {id: eid, author: author, title: title, message: message};
    $.ajax({type: "PUT", url: URL, contentType: "application/json", 
        data: JSON.stringify(newsitem), success: loadNewsItems, datatype: "json"});
}

function cancelEdit(eid)
{
    log("canceling " + eid);
    loadNewsItemDetails(eid);
}



