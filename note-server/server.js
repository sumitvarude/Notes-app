var http = require("http");
var dispatcher = require("httpdispatcher");
var library = require("./library.js").Tools;
var port = 8800;
var handleRequest = function (request, response){
  try {
        //log the request on console
        console.log(request.url);
        //Disptach
        dispatcher.dispatch(request, response);
    } catch(err) {
        console.log(err);
    }
};
var server = http.createServer(handleRequest);


dispatcher.onPost("/saveNote",function(request,response){
  var noteToSave = JSON.parse(request.body).note;
  if(library.saveTheNote(noteToSave)){
    response.writeHead(200,{'Content-Type':'text/plain'});
    response.end("true");
  }
  response.writeHead(500,{'Content-Type':'text/plain'});
  response.end("false");
});

dispatcher.onGet("/getNotes", function(request,response){
  response.writeHead(200,{'Content-type':'text/plain'});
  response.end("ghe tuzya notes");
})

server.listen(8800, function(){
  console.log("server is listening on post 8800");
});
