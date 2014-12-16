/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function (){
    $.getJSON(URL,fillnewsitems);

    
    $("#submitbtn").click(function (){
       var aut=$("#author").val();
       var tit=$("#title").val();
       var mes=$("#message").val();
       var newsitem={author:aut,title:tit,message:mes};
       $.ajax({
            type: 'POST',
            url: URL,
            contentType: 'application/json',
            data: JSON.stringify(newsitem),
            datatype:"json",
            success:function (data){
            }
       });
       $("#author").val("");
       $("#title").val("");
       $("#message").val("");
    });
});

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
            .attr("value", "+").on("click",function (){
                toggledata(item.id);
    })
            .appendTo(div);

    $("<div>")
            .attr("id", "details" + item.id)
            .appendTo(div);

    return div;
}

function fillnewsitems(data){
    var listnode=$("#entries").empty();
    $.each(data,function (i,item){
        var newsitem=createNewsItemElement(item);
        listnode.prepend(newsitem);
    });
}

function loaddetails(id){
    var details=$("#details" + id).empty();
    $.getJSON(URL+id,function (data){
       details.append(createDetailsElement(id,data)); 
    });
}
function createDetailsElement(id, item)
{
    return $("<table>")
            .append($("<tr>")
            .append($("<td>")
            .html("Author:"))
            .append($("<td>")
            .append($("<input>")
            .attr("id","author"+id)
            .attr("type","text")
            .attr("value",item.author)
            .attr("disabled","true"))))
            .append($("<tr>")
            .append($("<td>")
            .html("Title:"))
            .append($("<td>")
            .append($("<input>")
            .attr("id","title"+id)
            .attr("type","text")
            .attr("value",item.title)
            .attr("disabled","true"))))
            .append($("<tr>")
            .append($("<td>")
            .append($("<input>")
            .attr("id","remove"+id)
            .attr("type","button")
            .attr("value","remove")))
            .append($("<td>")
            .append($("<input>")
            .attr("id","edit"+id)
            .attr("type","button")
            .attr("value","edit").on("click",function (){
                editklik(id);
    }))));
    
}
function editklik(id){
    $("#author"+id).prop("disabled",false);
    $("#title"+id).prop("disabled",false);
}
function toggledata(id){
    var input=$("#toggle"+id);
    if(input.val()==="+"){
        input.val("-");
        loaddetails(id);
    }else{
        input.val("+");
        $("#details" + id).empty();
    }
}

var URL = "http://localhost:8080/ne/rest/news/";