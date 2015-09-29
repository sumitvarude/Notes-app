var dispatcher = require("httpdispatcher");
var library = require("./library.js").Tools;

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
});

module.exports.dispatcher = dispatcher;
