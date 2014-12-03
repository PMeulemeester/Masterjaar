/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function(){
   $("#canv").attr({"width":window.innerWidth,"height":window.innerHeight});
   $("#canv").mousedown(function(e){
       var mousex=e.pageX-$("#canv").offset().left;
       var mousey=e.pageY-$("#canv").offset().top;
      $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: e.offsetX, y: e.offsetY,
        radius: 20,
        start:0,end:360
      });
   });
   
   $("#clr").click(function (){
        makeboog("white");
        makeboogspiegel("white");
   });
});

function makeboogspiegel(color){
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 400, y: 450,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 460, y: 450,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 100, y: 390,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 160, y: 390,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 100, y: 330,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 160, y: 330,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 160, y: 290,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 110, y: 270,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 170, y: 270,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 140, y: 245,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 110, y: 420,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 150, y: 420,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 110, y: 360,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 150, y: 360,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 110, y: 300,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 150, y: 305,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 130, y: 450,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 130, y: 390,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 130, y: 330,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 135, y: 280,
        radius: 20,
        start:0,end:360
    });
}

function makeboog(color){
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 100, y: 450,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 160, y: 450,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 100, y: 390,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 160, y: 390,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 100, y: 330,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 160, y: 330,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 160, y: 290,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 110, y: 270,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 170, y: 270,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 140, y: 245,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 110, y: 420,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 150, y: 420,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 110, y: 360,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 150, y: 360,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 110, y: 300,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 150, y: 305,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 130, y: 450,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 130, y: 390,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: "white",
        x: 130, y: 330,
        radius: 20,
        start:0,end:360
    });
    $("#canv").drawArc({
        draggable: false,
        strokeStyle: '#000',
        strokeWidth: 0.2,
        fillStyle: color,
        x: 135, y: 280,
        radius: 20,
        start:0,end:360
    });
}
